package com.zrx.moonagain;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zrx.snowlibrary.utils.LogUtils;

/**
 * Created by Schnee on 2017/3/6.
 */

public abstract class StarBaseAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        super.onCreate(savedInstanceState);
        LogUtils.e("currentPage", getClass().getSimpleName());
    }
}
