package com.zrx.moonagain.activities;


import android.os.Bundle;

import com.zrx.moonagain.R;
import com.zrx.moonagain.StarBarBaseActivity;

/**
 * Created by Schnee on 2017/3/7.
 */

public class NewsDetailActivity extends StarBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_news_details);
        setTitle("详情");
        
    }
}
