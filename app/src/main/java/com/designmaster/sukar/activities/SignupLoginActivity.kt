package com.designmaster.sukar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.designmaster.sukar.R

class SignupLoginActivity : AppCompatActivity() {
    lateinit var loginll:LinearLayout
    lateinit var registerll:LinearLayout
    lateinit var skipll:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_register_lo)
        initUI()
    }

    private fun initUI() {
        loginll = findViewById<LinearLayout>(R.id.ar_acc_btn)
        registerll = findViewById<LinearLayout>(R.id.ar_acc_btn2)
        skipll = findViewById<LinearLayout>(R.id.skip_lo)
        loginll.setOnClickListener(View.OnClickListener { langResult() })
        skipll.setOnClickListener(View.OnClickListener { skipResult() })
        registerll.setOnClickListener(View.OnClickListener { signupResult() })
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