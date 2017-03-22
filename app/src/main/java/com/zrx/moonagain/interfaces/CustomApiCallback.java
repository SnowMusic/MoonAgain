package com.zrx.moonagain.interfaces;

import com.zrx.snowlibrary.utils.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Schnee on 2017/3/6.
 */

public class CustomApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        LogUtils.e("error-api", "---");
    }


}
