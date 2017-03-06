package com.zrx.moonagain.dto;

import java.util.ArrayList;

/**
 * Created by Schnee on 2017/2/16.
 */

public class BaseModel<T> {

    private ArrayList<T> response;

    public ArrayList<T> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<T> response) {
        this.response = response;
    }
}
