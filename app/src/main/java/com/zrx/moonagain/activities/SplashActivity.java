package com.zrx.moonagain.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrx.moonagain.BuildConfig;
import com.zrx.moonagain.R;
import com.zrx.moonagain.StarBaseAcitivity;
import com.zrx.moonagain.dto.ADModel;
import com.zrx.moonagain.dto.BaseModel;
import com.zrx.moonagain.dto.VersionModel;
import com.zrx.moonagain.interfaces.CustomApiCallback;
import com.zrx.moonagain.interfaces.IMoonService;
import com.zrx.moonagain.utils.ApiManager;
import com.zrx.moonagain.utils.IntentUtils;
import com.zrx.snowlibrary.utils.ClickUtil;
import com.zrx.snowlibrary.utils.DisplayPictureUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Schnee on 2017/2/20.
 */

public class SplashActivity extends StarBaseAcitivity {

    @BindView(R.id.tv_countdown)
    TextView tvCountdown;
    @BindView(R.id.iv_ad)
    ImageView ivAd;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what != 0) {
                tvCountdown.setText(what + "s");
                sendEmptyMessageDelayed(what - 1, 300);
            } else {
                jumpToNext();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_splash);
        ButterKnife.bind(this);

        handler.sendEmptyMessageDelayed(3, 1000);

        getADUrl();
        tvCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isNotFastClick()) {
                    handler.removeCallbacksAndMessages(null);
                    jumpToNext();
                }
            }
        });

    }


    private void jumpToNext() {
        startActivity(IntentUtils.toMainActivity(SplashActivity.this));
        finish();
    }

    public void getADUrl() {

//        Call<BaseModel<ADModel>> ads = ApiManager.getMoonService().getAD();
//        ads.enqueue(new CustomApiCallback<BaseModel<ADModel>>() {
//            @Override
//            public void onResponse(Call<BaseModel<ADModel>> call, Response<BaseModel<ADModel>> response) {
//                super.onResponse(call, response);
//                DisplayPictureUtil.showPicture(SplashActivity.this, ivAd, response.body().getResponse().getImgurl());
//            }
//
//            @Override
//            public void onFailure(Call<BaseModel<ADModel>> call, Throwable t) {
//                super.onFailure(call, t);
//            }
//        });


    }
}
