package com.zrx.moonagain.activities;


import android.os.Bundle;
import android.webkit.WebView;

import com.zrx.moonagain.R;
import com.zrx.moonagain.StarBarBaseActivity;
import com.zrx.moonagain.dto.NewsDetailModel;
import com.zrx.moonagain.interfaces.CustomApiCallback;
import com.zrx.moonagain.utils.ApiManager;
import com.zrx.moonagain.utils.IntentUtils;
import com.zrx.snowlibrary.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Schnee on 2017/3/7.
 */

public class NewsDetailActivity extends StarBarBaseActivity {

    @BindView(R.id.wb_detail)
    WebView wbDetail;

    int newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_news_details);
        ButterKnife.bind(this);
        newsId = getIntent().getIntExtra(IntentUtils.EXTRA_NEWS_ID, 0);
        initData();

    }

    private void initData() {
        Call<NewsDetailModel> detailModelCall = ApiManager.getMoonService().getNewsDetail(newsId);
        detailModelCall.enqueue(new CustomApiCallback<NewsDetailModel>() {
            @Override
            public void onResponse(Call<NewsDetailModel> call, Response<NewsDetailModel> response) {
                super.onResponse(call, response);
                wbDetail.loadDataWithBaseURL("",StringUtils.nullToEmpty(response.body().getBody()), "text/html", "utf-8","");
            }

            @Override
            public void onFailure(Call<NewsDetailModel> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
}
