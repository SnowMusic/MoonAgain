package com.zrx.snowlibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Schnee on 2017/3/6.
 */

public class ImageDisplayUtil {

    public static void showPicture(Context context, ImageView iv, String path) {
        Picasso.with(context).load(path).into(iv);
    }

}