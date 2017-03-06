package com.zrx.moonagain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zrx.moonagain.MoonBaseFragment;
import com.zrx.moonagain.R;

/**
 * Created by Schnee on 2017/3/6.
 */

public class ChannelFragment extends MoonBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_channel, null);
        return view;
    }
}
