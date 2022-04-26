package com.designmaster.sukar.util

import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import android.util.DisplayMetrics

object AppPrefs {

    private val PREF_KEY_LANGUAGE_SELECTED = "languageSelected"
    private val PREF_KEY_LOGIN = "userLogin"
    private val PREF_KEY_GUEST = "isGuest"
    private val PREF_KEY_USER_ID = "userId"
    private val PREF_KEY_USER_NAME = "userName"
    private val PREF_KEY_FULL_NAME = "fullName"
    private val PREF_KEY_FIRST_NAME = "firstName"
    private val PREF_KEY_LAST_NAME = "lastName"
    private val PREF_KEY_EMAIL_ID = "emailId"
    private val PREF_KEY_MOBILE = "mobileNo"
    private val PREF_KEY_LOCALE = "locale"
    private val PREF_KEY_CURRENCY_CODE = "currencyCode"
    private val PREF_KEY_DEVICE_TOKEN = "deviceToken"

    fun putBoolean(ctx: Context?, key: String, value: Boolean) {
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(ctx)
            .edit()
        prefsEditor.putBoolean(key, value).commit()
    }

    @JvmOverloads
    fun getBoolean(ctx: Context?, key: String, defaulValue: Boolean = false): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getBoolean(key, defaulValue)
    }

    fun putString(ctx: Context?, key: String, value: String?) {
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(ctx)
            .edit()
        prefsEditor.putString(key, value).commit()
    }

    fun getStringNotNull(ctx: Context, key: String): String? {
        return getString(ctx, key, "")
    }

    @JvmOverloads
    fun getString(ctx: Context?, key: String, defaultValue: String? = null): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getString(key, defaultValue)
    }

    fun putInt(ctx: Context, key: String, value: Int) {
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(ctx)
            .edit()
        prefsEditor.putInt(key, value).commit()
    }

    fun putDouble(ctx: Context, key: String, value: Long) {
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(ctx)
            .edit()
        prefsEditor.putLong(key, value).commit()
    }

    @JvmOverloads
    fun getDouble(ctx: Context?, key: String): Long? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getLong(key, 0)
    }

    fun clear(ctx: Context) {
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(ctx)
            .edit()
        prefsEditor.clear()
        prefsEditor.commit()
        //setLoggedIn(ctx, false)
    }

    @JvmOverloads
    fun getInt(ctx: Context, key: String, defaultValue: Int = 0): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getInt(key, defaultValue)

    }

    fun getLocale(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_LOCALE, MyApplication.LOCALE_ENGLISH)
    }

    fun isLocaleEnglish(ctx: Context?): Boolean {
        var value = true
        if (getString(
                ctx,
                PREF_KEY_LOCALE,
                MyApplication.LOCALE_ENGLISH
            ).equals(MyApplication.LOCALE_ARABIC, true)
        ) {
            value = false
        }
        return value
    }

    fun setLocale(ctx: Context, value: String?) {
        putString(ctx, PREF_KEY_LOCALE, value)
    }

    fun isLanguageSelected(ctx: Context?): Boolean {
        return getBoolean(ctx, PREF_KEY_LANGUAGE_SELECTED, false)
    }

    fun setLanguageSelected(ctx: Context?, value: Boolean) {
        putBoolean(ctx, PREF_KEY_LANGUAGE_SELECTED, value)
    }

    fun isLoggedIn(ctx: Context?): Boolean {
        return getBoolean(ctx, PREF_KEY_LOGIN, false)
    }

    fun setLoggedIn(ctx: Context?, value: Boolean) {
        putBoolean(ctx, PREF_KEY_LOGIN, value)
    }


    fun getUserId(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_USER_ID, "0")
    }

    fun setUserId(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_USER_ID, value)
    }

    fun getUserName(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_USER_NAME)
    }

    fun setUserName(ctx: Context, value: String) {
        putString(ctx, PREF_KEY_USER_NAME, value)
    }

    fun getFullName(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_FULL_NAME)
    }

    fun setFullName(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_FULL_NAME, value)
    }

    fun getEmail(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_EMAIL_ID)
    }

    fun setEmail(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_EMAIL_ID, value)
    }

    fun getFirstName(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_FIRST_NAME)
    }

    fun setFirstName(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_FIRST_NAME, value)
    }

    fun getLastName(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_LAST_NAME)
    }

    fun setLastName(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_LAST_NAME, value)
    }

    fun getMobile(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_MOBILE)
    }

    fun setMobile(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_MOBILE, value)
    }


    fun get_device_height(context: Context): Int {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay
            .getMetrics(metrics)
        return metrics.heightPixels
    }

    fun get_device_width(context: Context): Int {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay
            .getMetrics(metrics)
        return metrics.widthPixels
    }

    fun setCurrency(ctx: Context, value: String?) {
        putString(ctx, PREF_KEY_CURRENCY_CODE, value)
    }

    fun getCurrency(ctx: Context): String {
        return getString(ctx, PREF_KEY_CURRENCY_CODE, "KD") + " "
    }


    fun getDeviceToken(ctx: Context?): String? {
        return getString(ctx, PREF_KEY_DEVICE_TOKEN, null)
    }

    fun setDeviceToken(ctx: Context?, value: String?) {
        putString(ctx, PREF_KEY_DEVICE_TOKEN, value)
    }
}