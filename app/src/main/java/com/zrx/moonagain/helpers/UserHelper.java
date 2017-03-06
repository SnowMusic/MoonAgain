package com.zrx.moonagain.helpers;

import android.content.Context;

/**
 * Created by Schnee on 2017/3/6.
 */

public class UserHelper {

    private static UserHelper helper = null;

    private Context context;

    public static UserHelper getInstance() {
        if (helper == null) helper = new UserHelper();
        return helper;
    }

    public boolean hasLogIn() {
        return false;
    }
}
