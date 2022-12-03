package com.designmaster.sukar.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.designmaster.sukar.R
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.MyApplication

class LanguageSelectionActivity : AppCompatActivity() {
    lateinit var englishBtn:Button
    lateinit var arabicBtn: Button
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_lang_lo)
        initUI()
    }
    private fun initUI() {
        englishBtn = findViewById<Button>(R.id.englishBtn)
        arabicBtn = findViewById<Button>(R.id.arabicBtn)

        englishBtn.setOnClickListener(View.OnClickListener {
            //langResult()

            AppPrefs.setLocale(this, MyApplication.LOCALE_ENGLISH)
            AppPrefs.setLanguageSelected(this,true)

            val mainIntent = Intent(this@LanguageSelectionActivity, SignupLoginActivity::class.java)
            startActivity(mainIntent)
            finish()


        })
        arabicBtn.setOnClickListener(View.OnClickListener {
           // langResult()

            AppPrefs.setLocale(this, MyApplication.LOCALE_ARABIC)
            AppPrefs.setLanguageSelected(this,true)

            val mainIntent = Intent(this@LanguageSelectionActivity, SignupLoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        })

    }

    private fun langResult() {
        val mainIntent = Intent(this, SignupLoginActivity::class.java)
        startActivity(mainIntent)
        finish()

    }
    override fun onBackPressed() {

        finish()
    }
    companion object{
        var sharedPrefFile = "Sukarpreference"
    }
}