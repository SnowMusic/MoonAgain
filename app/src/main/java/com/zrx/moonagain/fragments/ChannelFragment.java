package com.zrx.moonagain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zrx.moonagain.MoonBaseFragment;
import com.zrx.moonagain.R;
import com.zrx.snowlibrary.views.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Schnee on 2017/3/6.
 */

public class ChannelFragment extends MoonBaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_newslist)
    RecyclerView rvNewslist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_channel, null);
        ButterKnife.bind(this, view);
        ArrayList<String> a = new ArrayList<>();
        a.add("http://img5.imgtn.bdimg.com/it/u=3577875476,238353561&fm=23&gp=0.jpg");
        a.add("http://img3.imgtn.bdimg.com/it/u=1497822951,4264477876&fm=23&gp=0.jpg");
        a.add("http://img5.imgtn.bdimg.com/it/u=3577875476,238353561&fm=23&gp=0.jpg");
        a.add("http://img3.imgtn.bdimg.com/it/u=1497822951,4264477876&fm=23&gp=0.jpg");
        banner.setData(a);
        return view;
    }
}
