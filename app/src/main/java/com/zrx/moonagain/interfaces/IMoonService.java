package com.zrx.moonagain.interfaces;

import com.zrx.moonagain.dto.ADModel;
import com.zrx.moonagain.dto.BaseModel;
import com.zrx.moonagain.dto.ChannelModel;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Schnee on 2017/2/16.
 */

public interface IMoonService {

    @GET("/channel.php?ctype=all")
    void getAllChannel(CustomApiCallback<BaseModel<ChannelModel>> channelModels);

    @GET("/newslist.php?type=myad")
    void getAD(CustomApiCallback<BaseModel<ADModel>> adModels);

}


