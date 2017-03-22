package com.zrx.moonagain.dto;

/**
 * Created by Schnee on 2017/3/9.
 */

public class BaseModel<T> {

    private T response;

    public void setResponse(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }
}
