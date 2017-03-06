package com.zrx.moonagain;

import android.app.Application;

/**
 * Created by Schnee on 2017/2/20.
 */

public class MoonApplication extends Application {

    public static MoonApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
