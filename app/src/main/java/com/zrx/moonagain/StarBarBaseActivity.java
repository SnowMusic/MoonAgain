package com.zrx.moonagain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrx.snowlibrary.utils.ClickUtil;

/**
 * Created by Schnee on 2017/3/6.
 */

public class StarBarBaseActivity extends StarBaseAcitivity implements View.OnClickListener{

    protected ImageView ivBack;

    private TextView tvBarRight, tvTitle;

    private ActionBar actionBar;

    private View tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        initBaseView();
    }

    private void initBaseView() {
        tool_bar = findViewById(R.id.tool_bar);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvBarRight = (TextView) findViewById(R.id.tv_bar_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivBack.setOnClickListener(this);
        tvBarRight.setOnClickListener(this);
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(this).inflate(R.layout.aty_moon_tool_bar, null);
        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setElevation(0);

        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;
        if (v == ivBack) {
            onBarLeftClick();
        } else if (v == tvBarRight) {
            onBarRightClick();
        }
    }

    protected void onBarRightClick() {
    }

    protected void setTitle(String strTitle) {
        tvTitle.setText(strTitle);
    }

    protected void onBarLeftClick() {
        finish();
    }

    public View getTool_bar() {
        return tool_bar;
    }

    public TextView getTvBarRight() {
        return tvBarRight;
    }
}

