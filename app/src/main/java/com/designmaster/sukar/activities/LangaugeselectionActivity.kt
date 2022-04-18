package com.designmaster.sukar.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.designmaster.sukar.R
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.MyApplication

class LangaugeselectionActivity : AppCompatActivity() {
    lateinit var engbtn:LinearLayout
    lateinit var arabbtn:LinearLayout
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_lang_lo)
        initUI()
    }
    private fun initUI() {
        engbtn = findViewById<LinearLayout>(R.id.ar_acc_btn2)
        arabbtn = findViewById<LinearLayout>(R.id.ar_acc_btn22)
        engbtn.setOnClickListener(View.OnClickListener {
            //langResult()
//            sharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
//            val editor: SharedPreferences.Editor =  sharedPreferences.edit()
//            editor.putString("language","English")
//            editor.apply()
//            editor.commit()
//            val sharedIdValue = sharedPreferences.getString("language","defaultlanguage")
//            if(sharedIdValue.equals("defaultlanguage")){
//                MyApplication.LANGUAGE_SELECTION="default name: ${sharedIdValue}"
//            }else{
//                MyApplication.LANGUAGE_SELECTION=sharedIdValue.toString()
//            }
//            if (MyApplication.LANGUAGE_SELECTION.equals("English")) {
//                val mainIntent = Intent(this@LangaugeselectionActivity, SignupLoginActivity::class.java)
//                startActivity(mainIntent)
//                finish()
//            }


            AppPrefs.setLocale(this, MyApplication.LOCALE_ENGLISH)
            AppPrefs.setLanguageSelected(this,true)

            val mainIntent = Intent(this@LangaugeselectionActivity, SignupLoginActivity::class.java)
            startActivity(mainIntent)
            finish()


        })
        arabbtn.setOnClickListener(View.OnClickListener {
           // langResult()
//            sharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
//            val editor: SharedPreferences.Editor =  sharedPreferences.edit()
//            editor.putString("language","Arabic")
//            editor.apply()
//            editor.commit()
//            val sharedIdValue = sharedPreferences.getString("language","defaultlanguage")
//            if(sharedIdValue.equals("defaultlanguage")){
//                MyApplication.LANGUAGE_SELECTION="default name: ${sharedIdValue}"
//            }else{
//                MyApplication.LANGUAGE_SELECTION=sharedIdValue.toString()
//            }
//            if (MyApplication.LANGUAGE_SELECTION.equals("Arabic")) {
//                val mainIntent = Intent(this@LangaugeselectionActivity, SignupLoginActivity::class.java)
//                startActivity(mainIntent)
//                finish()
//            }

            AppPrefs.setLocale(this, MyApplication.LOCALE_ARABIC)
            AppPrefs.setLanguageSelected(this,true)

            val mainIntent = Intent(this@LangaugeselectionActivity, SignupLoginActivity::class.java)
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