package com.zrx.moonagain.views;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zrx.moonagain.R;
import com.zrx.snowlibrary.utils.DisplayPictureUtil;
import com.zrx.snowlibrary.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Schnee on 2017/3/23.
 */

public class BannerContentView extends RelativeLayout {

    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.tv_banner)
    TextView tvBanner;

    public BannerContentView(Context context) {
        this(context, null);
    }

    public BannerContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_banner, this);
        ButterKnife.bind(this);
    }

    public void setData(String title, String path) {
        tvBanner.setText(StringUtils.nullToEmpty(title));
        DisplayPictureUtil.showPicture(getContext(), ivBanner, path);
    }
}
