package com.designmaster.sukar.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.LoginActivity.Companion.sharedPrefFile
import com.designmaster.sukar.util.AppConstants
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.MyApplication
import com.designmaster.sukar.util.MyPreferenceMangaer
import kotlin.math.floor


class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MyPreferenceMangaer.intialize(this)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val sharedIdValue = sharedPreferences.getString("id_key","0")
        val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
             val languageselection = sharedPreferences.getString("language", "defaultlanguage")
        if(sharedIdValue.equals("0") && sharedNameValue.equals("defaultname")){

            MyApplication.userid="default id: ${sharedIdValue}"
            MyApplication.userloginsuccess="default name: ${sharedNameValue}"


        }else{
            MyApplication.userloginsuccess=sharedNameValue.toString()
            MyApplication.userid=sharedIdValue.toString()

        }
        if (sharedIdValue.equals("defaultlanguage")){
            MyApplication.LANGUAGE_SELECTION="default language: ${languageselection}"
        }else{
            MyApplication.LANGUAGE_SELECTION = languageselection
        }

        if (MyApplication.userloginsuccess =="isloginsuccess") {


            MyApplication.userid=sharedIdValue.toString()
            MyApplication.userloginsuccess=sharedNameValue.toString()
            MyApplication.isLanguageselected =
                MyPreferenceMangaer.getInstance().getBoolean(AppConstants.languageselected)!!
            if (MyApplication.isLanguageselected == true) {
                MyApplication.language =
                    MyPreferenceMangaer.getInstance().getString(AppConstants.language)!!
            }
        }else{

            MyApplication.userid ="0"

            MyApplication.isLanguageselected =
                MyPreferenceMangaer.getInstance().getBoolean(AppConstants.languageselected)!!
            if (MyApplication.isLanguageselected == false) {
                MyApplication.language =
                    MyPreferenceMangaer.getInstance().getString(AppConstants.language)!!
            }
        }
        Handler().postDelayed({

//            if (MyApplication.userloginsuccess =="isloginsuccess") {
////                val mainIntent = Intent(this@SplashActivity, HomeActivity::class.java)
//                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
//                startActivity(mainIntent)
//                finish()
//            }else{
//                  val mainIntent = Intent(this@SplashActivity, LangaugeselectionActivity::class.java)
//                    startActivity(mainIntent)
//                    finish()
//
//
//            }


            if (AppPrefs.isLanguageSelected(this)) {

                if (AppPrefs.isLoggedIn(this)) {
                    val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }else{
                    val mainIntent = Intent(this@SplashActivity, SignupLoginActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }



            }else{
                val mainIntent = Intent(this@SplashActivity, LangaugeselectionActivity::class.java)
                startActivity(mainIntent)
                finish()
            }

//            if (AppPrefs.isLoggedIn(this)) {
//
//                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
//                startActivity(mainIntent)
//                finish()
//            }else {
//                val mainIntent = Intent(this@SplashActivity, LangaugeselectionActivity::class.java)
//                startActivity(mainIntent)
//                finish()
//            }





        }, 500)

    }




}