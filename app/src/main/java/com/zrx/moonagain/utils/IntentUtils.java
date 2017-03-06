package com.zrx.moonagain.utils;

import android.content.Context;
import android.content.Intent;

import com.zrx.moonagain.activities.LoginActivity;
import com.zrx.moonagain.activities.MainActivity;

/**
 * Created by Schnee on 2017/3/6.
 */

public class IntentUtils {

    public static Intent toMainActivity(Context ctx) {
        return new Intent(ctx, MainActivity.class);
    }

    public static Intent toLoginActivity(Context ctx) {
        return new Intent(ctx, LoginActivity.class);
    }
}
