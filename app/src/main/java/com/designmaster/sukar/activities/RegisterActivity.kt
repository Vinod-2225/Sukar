package com.designmaster.sukar.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.designmaster.sukar.R
import com.designmaster.sukar.models.SignUpResponse
import com.designmaster.sukar.util.*

class RegisterActivity : BaseActivity(), ApiCallListener, View.OnClickListener {
lateinit var termscond:LinearLayout
    lateinit var registerll:LinearLayout
    lateinit var backicon: AppCompatImageView

    var nameEdit: EditText? = null
    var lnameEdit: EditText? = null
    var mobileEdit: EditText? = null
    var emailEdit: EditText? = null
    var cntrycodeedit: EditText? = null
    var etmobilenor: EditText? = null
    var passwordEdit: EditText? = null
    var confirmPasswordEdit: EditText? = null

    var strname:String=""
    var strlname:String=""
    var stremail:String=""
    var strcntrycode:String=""
    var strmobilenor:String=""
    var strpwd:String=""
    var strcnfpwd:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_lo)
        initUI()
    }

    private fun initUI() {
        backicon=findViewById<AppCompatImageView>(R.id.backicon)
        backicon.setOnClickListener(this)
        termscond = findViewById<LinearLayout>(R.id.terms_condt_lo)
        nameEdit = findViewById<EditText>(R.id.first_name_edt)
        lnameEdit = findViewById<EditText>(R.id.last_name_edt)
        emailEdit = findViewById<EditText>(R.id.email_edt)
        mobileEdit = findViewById<EditText>(R.id.mobile_no)

        cntrycodeedit = findViewById<EditText>(R.id.cntrycode)

        passwordEdit = findViewById<EditText>(R.id.create_pwd_edt)
        confirmPasswordEdit = findViewById<EditText>(R.id.confirm_pwd_edt)
        registerll = findViewById<LinearLayout>(R.id.ar_acc_btn2)
        termscond.setOnClickListener(View.OnClickListener { tersmResult() })

        registerll.setOnClickListener(View.OnClickListener {
            //langResult()
            strname = nameEdit!!.text.toString()
            strlname = lnameEdit!!.text.toString()
            stremail = emailEdit!!.text.toString()
            strcntrycode = cntrycodeedit!!.text.toString()
            strmobilenor = mobileEdit!!.text.toString()
            strpwd = passwordEdit!!.text.toString()
            strcnfpwd = confirmPasswordEdit!!.text.toString()
            if (!isValidPassword(strname)) {
                nameEdit!!.setError("Enter Firstname")
            }else if (!isValidPassword(strlname)) {
                lnameEdit!!.setError("Enter Lastname")
            }else if (!isValidEmail(stremail)) {
                emailEdit!!.setError("Enter valid Email")
            }else if (!isValidPassword(strcntrycode)) {
                cntrycodeedit!!.setError("Enter Countrycode")
            }else if (!isValidMobile1(strmobilenor)) {
                mobileEdit!!.setError("Enter ContactNumber")
            }else if (!isValidPasswordlegnthmin(strpwd)) {
                passwordEdit!!.setError("Password minimum length 6 and maximum 15 characters!")
            } else if (!isValidPassword(strcnfpwd)) {
                confirmPasswordEdit!!.setError("confirm password should match with passowrd")
            } else if (!strcnfpwd.equals(strpwd)) {
                confirmPasswordEdit!!.setError("confirm password should match with passowrd")
            }else {
                userRegistration()
            }
        })
    }

    private fun userRegistration() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.userRegistration(
                strname,
               strlname,
                stremail,
                strcntrycode,
                strmobilenor,
                strpwd,
                strcnfpwd


            ),
            this,
            ApiConstants.REQUEST_CODE.USER_REGISTRATION
        )

    }



    private fun tersmResult() {
        val mainIntent = Intent(this, TermsActivity::class.java)
        startActivity(mainIntent)
        finish()

    }

    private fun langResult() {
        val mainIntent = Intent(this, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()

    }
    override fun onBackPressed() {
        // super.onBackPressed()
        val mainIntent = Intent(this, LangaugeselectionActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.backicon->{
                val mainIntent = Intent(this, LangaugeselectionActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }
    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.USER_REGISTRATION -> {
                val apiResponse = RetrofitApiCall.getPayload(SignUpResponse::class.java, response)
                if (apiResponse.output.success == 1) {
                    showToast(apiResponse.output.message)
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    this.finish()
                } else {
                    showToast(apiResponse.output.message)
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