package com.zrx.moonagain

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.zrx.snowlibrary.utils.LogUtils

/**
 * Created by Schnee on 2017/3/6.
 */

abstract class StarBaseAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
        super.onCreate(savedInstanceState)
        LogUtils.e("currentPage", javaClass.getSimpleName())


    }
}
