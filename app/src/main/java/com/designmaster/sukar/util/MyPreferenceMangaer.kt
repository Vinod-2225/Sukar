package com.designmaster.sukar.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class MyPreferenceMangaer(){
    lateinit var sharedPreferences: SharedPreferences
    constructor(context: Context):this(){
        ctx = context
        sharedPreferences = ctx.getSharedPreferences("Fit", Context.MODE_PRIVATE)
    }
    fun setString(key: String, value: String) {
        val editor = getPrefObject().edit()
        editor.putString(key, value)
        editor.commit()
    }
    fun setBoolean(key: String, value: Boolean) {
        val editor = getPrefObject().edit()
        editor.putBoolean(key, value)
        editor.commit()
    }
    fun setInt(key: String, value: Int) {
        val editor = getPrefObject().edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(key: String): Int {
        return getPrefObject().getInt(key, 0)
    }

    fun getString(key: String): String? {
        return getPrefObject().getString(key, "")
    }
    fun getBoolean(key: String): Boolean? {
        return getPrefObject().getBoolean(key, false)
    }

    fun getPrefObject(): SharedPreferences {
        return sharedPreferences
    }

    fun getEditObject(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance : MyPreferenceMangaer? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var ctx: Context
        fun  intialize(context: Context) {
            if (instance == null)  // NOT thread safe!
                instance = MyPreferenceMangaer(context)
        }
        fun getInstance():MyPreferenceMangaer
        {
            return instance!!
        }
    }
}