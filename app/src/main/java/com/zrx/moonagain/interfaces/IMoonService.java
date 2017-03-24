package com.zrx.moonagain.interfaces;

import com.zrx.moonagain.dto.ADModel;
import com.zrx.moonagain.dto.BaseModel;
import com.zrx.moonagain.dto.DailyNewsListModel;
import com.zrx.moonagain.dto.LatestNewsModel;
import com.zrx.moonagain.dto.NewsDetailModel;
import com.zrx.moonagain.dto.ThemeNewsModel;
import com.zrx.moonagain.dto.VersionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Schnee on 2017/2/16.
 */

public interface IMoonService {

    @GET("start-image/720*1184")
    Call<BaseModel<ADModel>> getAD();

    @GET("version/android/2.3.0")
    Call<VersionModel> getVersion();

    @GET("themes")
    Call<DailyNewsListModel> getThemes();

    @GET("theme/{id}")
    Call<ThemeNewsModel> getThemeNews(@Path("id") int id);

    @GET("news/latest")
    Call<LatestNewsModel> getLatestNews();

    @GET("news/{id}")
    Call<NewsDetailModel> getNewsDetail(@Path("id") int id);

}


