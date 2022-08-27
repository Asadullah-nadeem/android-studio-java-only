package com.livescorescrickets.livescores;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

import java.util.HashMap;
import java.util.Map;

public class YourApplication extends Application {
    public static YourApplication mInstance;
    private static final String TAG = YourApplication.class.getSimpleName();
    private static AppOpenManager appOpenManager;
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(2);
        mInstance = this;
        MultiDex.install(this);

        Map<String, Object> remoteConfigDefaults = new HashMap();


        appOpenManager = new AppOpenManager(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
