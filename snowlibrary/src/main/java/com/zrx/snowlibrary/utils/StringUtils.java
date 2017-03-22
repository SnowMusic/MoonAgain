package com.zrx.snowlibrary.utils;

import android.text.TextUtils;

/**
 * Created by Schnee on 2017/3/22.
 */

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isNotEmpty(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static String nullToEmpty(String str) {
        if (TextUtils.isEmpty(str)) return "";
        return str;
    }
}
