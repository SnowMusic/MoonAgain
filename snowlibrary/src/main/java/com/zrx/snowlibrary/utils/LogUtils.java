package com.zrx.snowlibrary.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Schnee on 2017/3/6.
 */

public class LogUtils {

    private LogUtils(){

    }
    final static String TAG = "moon";

    public static void e(String tag, Object msg) {
        Log.e(TextUtils.isEmpty(tag) ? TAG : tag, msg.toString());
    }


}
