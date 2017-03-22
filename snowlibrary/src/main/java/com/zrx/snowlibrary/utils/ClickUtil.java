package com.zrx.snowlibrary.utils;

/**
 * Created by Schnee on 2017/3/6.
 */

public class ClickUtil {

    private static long lastClickTime = 0;

    //点击间隔时间
    private static final int fastClickTimeGap = 500;

    public static boolean isFastClick() {
        return needGiveResponse(fastClickTimeGap);
    }

    private static boolean needGiveResponse(int timeGap) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime > fastClickTimeGap) {
            lastClickTime = time;
            return false;
        }
        return true;
    }

    public static boolean isNotFastClick() {
        return !isFastClick();
    }

    //退出间隔时间
    private static final int finishClickTimeGap = 2000;

    public static boolean canFinish() {
        return needGiveResponse(finishClickTimeGap);
    }
}
