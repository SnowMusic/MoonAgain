package com.zrx.snowlibrary.utils;

/**
 * Created by Schnee on 2017/3/6.
 */

public class ClickUtil {

    private static long lastClickTime = 0;

    //点击间隔时间
    private static final int fastClickTimeGap = 500;

    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime > fastClickTimeGap) return false;
        lastClickTime = time;
        return true;
    }

    public static boolean isNotFastClick(){
        return !isFastClick();
    }
}
