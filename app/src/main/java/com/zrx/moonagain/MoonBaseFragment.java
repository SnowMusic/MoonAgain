package com.zrx.moonagain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zrx.snowlibrary.utils.LogUtils;

/**
 * Created by Schnee on 2017/3/6.
 */

public class MoonBaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("currentFragemnt", getClass().getSimpleName());
    }
}
