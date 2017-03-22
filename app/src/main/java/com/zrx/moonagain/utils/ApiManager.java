package com.zrx.moonagain.utils;

import com.zrx.moonagain.BuildConfig;
import com.zrx.moonagain.interfaces.IMoonService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Schnee on 2017/3/9.
 */

public class ApiManager {

    private static final String baseUrl = BuildConfig.BASE_URL;

    private static ApiManager utils;

    private static ApiManager getUtilsInstance() {
        utils = new ApiManager();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        moonService = retrofit.create(IMoonService.class);
        return utils;
    }

    private static IMoonService moonService;

    public static IMoonService getMoonService() {
        if (utils == null) {
            getUtilsInstance();
        }
        return moonService;
    }


}
