package com.zrx.moonagain.interfaces;

import com.zrx.snowlibrary.utils.LogUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Schnee on 2017/3/6.
 */

public class CustomApiCallback<T> implements Callback<T> {

    @Override
    public void success(T t, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {
        LogUtils.e("error-api", error.getMessage());
    }
}
