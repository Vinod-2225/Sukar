package com.designmaster.sukar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.designmaster.sukar.R
import com.designmaster.sukar.util.AppPrefs

class SignupLoginActivity : AppCompatActivity() {
    lateinit var loginBtn:Button
    lateinit var signUpBtn:Button
    lateinit var skipBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppPrefs.isLocaleEnglish(this)) {
            setContentView(R.layout.login_register)
        }else{
            setContentView(R.layout.login_register_ar)
        }
        initUI()
    }

    private fun initUI() {

        loginBtn = findViewById<Button>(R.id.loginBtn)
        signUpBtn = findViewById<Button>(R.id.signUpBtn)
        skipBtn = findViewById<Button>(R.id.skipBtn)

        loginBtn.setOnClickListener(View.OnClickListener { langResult() })

        signUpBtn.setOnClickListener(View.OnClickListener { signupResult() })

        skipBtn.setOnClickListener(View.OnClickListener { skipResult() })

    }

    private fun signupResult() {
        val mainIntent = Intent(this, RegisterActivity::class.java)
        startActivity(mainIntent)
        finish()

    }

    private fun langResult() {
        val mainIntent = Intent(this, LoginActivity::class.java)
        startActivity(mainIntent)
        finish()

    }
    private fun skipResult() {
//        val mainIntent = Intent(this, HomeActivity::class.java)
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()

    }
//    override fun onBackPressed() {
//        // super.onBackPressed()
//        val mainIntent = Intent(this, LangaugeselectionActivity::class.java)
//        startActivity(mainIntent)
//        finish()
//    }
}