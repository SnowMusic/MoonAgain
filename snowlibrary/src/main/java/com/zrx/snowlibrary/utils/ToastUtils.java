package com.zrx.snowlibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Schnee on 2017/2/17.
 */

public class ToastUtils {

    private ToastUtils(){}
    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

}
