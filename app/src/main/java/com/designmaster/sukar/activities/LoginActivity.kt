package com.designmaster.sukar.activities

import LoginResponse
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.designmaster.sukar.R
import com.designmaster.sukar.models.ApiResponse
import com.designmaster.sukar.util.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class LoginActivity : BaseActivity(), ApiCallListener, View.OnClickListener {
    lateinit var loginll: LinearLayout
    lateinit var registerll: LinearLayout
    lateinit var gimg: ImageView
    lateinit var fimg: ImageView
    lateinit var forgottv: TextView
    lateinit var email_add_edt: EditText
    lateinit var pwd_edt: EditText
    var stremail: String = ""
    var strpwd: String = ""
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_lonew)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(
                        ContentValues.TAG,
                        "Fetching FCM registration token failed",
                        task.exception
                    )
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                AppPrefs.setDeviceToken(this, token)
            })

        initUI()
    }

    private fun initUI() {
        forgottv = findViewById<TextView>(R.id.forgot_txt)
        loginll = findViewById<LinearLayout>(R.id.loginll)
        gimg = findViewById<ImageView>(R.id.google_plus_img)
        fimg = findViewById<ImageView>(R.id.facebook_img)
        email_add_edt = findViewById<EditText>(R.id.email_add_edt)
        pwd_edt = findViewById<EditText>(R.id.pwd_edt)
        gimg.setOnClickListener(View.OnClickListener { langResult() })
        fimg.setOnClickListener(View.OnClickListener { langResult() })
        loginll.setOnClickListener(View.OnClickListener {
            stremail = email_add_edt.text.toString()
            strpwd = pwd_edt.text.toString()
            if (!isValidEmail(stremail)) {
                email_add_edt!!.setError("Enter valid Email")
            } else if (!isValidPassword(strpwd)) {
                pwd_edt!!.setError("Enter Password")
            } else {
                loginapi()
            }
            //langResult()
        })
        forgottv.setOnClickListener(this)

    }

    private fun loginapi() {

        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.userLogin(
                stremail,
                strpwd,
                AppPrefs.getDeviceToken(this).toString(),
                "android"
                ),
            this,
            ApiConstants.REQUEST_CODE.USER_LOGIN
        )
    }

    private fun langResult() {
        val mainIntent = Intent(this, HomeActivity::class.java)
        startActivity(mainIntent)
        finish()

    }

    override fun onBackPressed() {
        // super.onBackPressed()
        val mainIntent = Intent(this, SignupLoginActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.forgot_txt -> {
                val inflater =
                    this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                /*  val popupView: View = if(DesignMoveinApp.instance.language.equals("English")||DesignMoveinApp.instance.language.equals("")) {
  */
                val popupView: View = inflater.inflate(R.layout.forgotpassword, null)


                /* }else{
                     inflater.inflate(R.layout.activity_forgot_password_ar, null)
                 }
 */
                val width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                val height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                val focusable = true // lets taps outside the popup also dismiss it

                val popupWindow = PopupWindow(popupView, width, height, focusable)
                popupWindow.setAnimationStyle(R.style.Animation);

                popupWindow.showAtLocation(v, Gravity.CENTER, 10, 10)
                val email = popupView.findViewById(R.id.etemail) as EditText

                val cancel = popupView.findViewById(R.id.cancel) as Button
                val submit = popupView.findViewById(R.id.reset_pwd) as Button
                cancel.setOnClickListener {
                    popupWindow.setAnimationStyle(R.style.Animation);
                    popupWindow.dismiss()

                }
                submit.setOnClickListener {
                    fwdstr = email.text.toString()
                    if (!isValidPassword(fwdstr)) {
                        Toast.makeText(this, "Enter your Email", Toast.LENGTH_LONG).show()
                        email.setError("Enter your Email")

                    } else {
                        // forgot()
                        popupWindow.setAnimationStyle(R.style.Animation);
                        popupWindow.dismiss()
//Toast.makeText(context,"password sent successfully to your mail",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


    companion object {
        var fwdstr: String = ""
        var sharedPrefFile = "Sukarpreference"
    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.USER_LOGIN -> {

                val apiResponse1 = RetrofitApiCall.getPayload(
                    ApiResponse::class.java,
                    response
                )

                if (apiResponse1.output.success == 1) {
                    val apiResponse = RetrofitApiCall.getPayload(
                        LoginResponse::class.java,
                        response
                    )
                    if (apiResponse.output.success == 1) {
//                    sharedPreferences =
//                        this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
//                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
//                    editor.putString("id_key", apiResponse.output.data.get(0).id.toString())
//                    editor.putString("name_key", "isloginsuccess")
//
//                    editor.apply()
//                    editor.commit()
//                    val sharedIdValue = sharedPreferences.getString("id_key", "0")
//                    val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
//                    if (sharedIdValue.equals("0") && sharedNameValue.equals("defaultname")) {
//
//                        MyApplication.userid = "default id: ${sharedIdValue}"
//                        MyApplication.userloginsuccess = "default name: ${sharedNameValue}"
//
//                    } else {
//                        MyApplication.userloginsuccess = sharedNameValue.toString()
//                        MyApplication.userid = sharedIdValue.toString()
//
//
//                    }
//                    if (MyApplication.userloginsuccess == "isloginsuccess") {
//
//
//                        MyApplication.userid = sharedIdValue.toString()
//                        MyApplication.userloginsuccess = sharedNameValue.toString()
//                        MyApplication.isLanguageselected =
//                            MyPreferenceMangaer.getInstance()
//                                .getBoolean(AppConstants.languageselected)!!
//                        if (MyApplication.isLanguageselected == true) {
//                            MyApplication.language =
//                                MyPreferenceMangaer.getInstance().getString(AppConstants.language)!!
//                        }
//                    } else {
//
//                        MyApplication.userid = "0"
//
//
//                        MyApplication.isLanguageselected =
//                            MyPreferenceMangaer.getInstance()
//                                .getBoolean(AppConstants.languageselected)!!
//                        if (MyApplication.isLanguageselected == false) {
//                            MyApplication.language =
//                                MyPreferenceMangaer.getInstance().getString(AppConstants.language)!!
//                        }
//                    }
//                    MyPreferenceMangaer.getInstance().setBoolean(
//                        AppConstants.isLoginSuc, true
//                    )



                        AppPrefs.setLoggedIn(this, true)
                        AppPrefs.setUserId(this, apiResponse.output.data.get(0).id.toString())
                        AppPrefs.setUserName(this, apiResponse.output.data.get(0).fname.toString()+" "+apiResponse.output.data.get(0).lname.toString())

                        val mainIntent = Intent(this, MainActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    } else {
                        showToast("Invalid User.Please try again later.")
                    }
                }else{
                    showToast("Invalid User.Please try again later.")
                }


            }


        }
        hideProgress()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }

}