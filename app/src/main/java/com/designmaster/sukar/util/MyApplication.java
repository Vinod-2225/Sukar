package com.designmaster.sukar.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static Context context;
    public static String userid = "0";
    public static String userloginsuccess = "";
    public static String LANGUAGE_SELECTION = "";
    public static Boolean isLanguageselected = false;
    public static String language = "";
    public static String LOCALE_ENGLISH = "en";
    public static String LOCALE_ARABIC = "ar";

    public void onCreate() {
        super.onCreate();
        setLocal(getApplicationContext());
        mInstance = this;
        context = this;
/*        OneSignal.startInit(this)
                .setNotificationReceivedHandler(new NotificationReceivedHelper())
                .setNotificationOpenedHandler(new NotificationOpenedHelper())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();*/
        //Stetho.initializeWithDefaults(this);
        //currencyCode=AppPrefs.getCurrency(context);
    }
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleUtils.updateConfig(this, newConfig);
    }
    public void setLocal(Context mContext) {
        //String lang = AppPrefs.INSTANCE.getLocale(this);
       // Log.e("MyApplication Locale", lang);
       // LocaleUtils.setLocale(new Locale(lang));
        LocaleUtils.updateConfig(this, mContext.getResources().getConfiguration());
    }

    public static Context getContext() {
        return context;
    }
}
