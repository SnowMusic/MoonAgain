package com.zrx.moonagain.activities

import android.os.Bundle

import com.zrx.moonagain.R
import com.zrx.moonagain.StarBarBaseActivity

/**
 * Created by Schnee on 2017/3/6.
 */

class LoginActivity : StarBarBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_login)
        setTitle("登录")


    }
}
