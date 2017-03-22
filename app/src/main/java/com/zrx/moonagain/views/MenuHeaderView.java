package com.zrx.moonagain.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zrx.moonagain.R;
import com.zrx.moonagain.helpers.UserHelper;
import com.zrx.moonagain.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Schnee on 2017/3/22.
 */

public class MenuHeaderView extends RelativeLayout {

    Context context;

    @BindView(R.id.iv_portrit)
    ImageView iv_portrit;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_favo)
    TextView tv_favo;
    @BindView(R.id.tv_download)
    TextView tv_download;

    public MenuHeaderView(Context context) {
        this(context, null);
    }

    public MenuHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.nav_header_main, this);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.nav_header_height));
        int paddingRight = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        setPadding(paddingRight, 0, paddingRight, 0);
        setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setLayoutParams(params);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        changeStatus();
    }

    private void changeStatus() {
        if (UserHelper.getInstance().hasLogIn()) {
            tv_desc.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.VISIBLE);
            tv_login.setVisibility(View.GONE);
        } else {
            tv_desc.setVisibility(View.GONE);
            tv_name.setVisibility(View.GONE);
            tv_login.setVisibility(View.VISIBLE);
        }
        tv_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    context.startActivity(IntentUtils.toLoginActivity(context));
            }
        });

    }
}
