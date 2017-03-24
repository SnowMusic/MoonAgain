package com.zrx.moonagain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;
import com.zrx.moonagain.R;
import com.zrx.moonagain.adapters.ChannelAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Schnee on 2017/2/10.
 */

public class NewsFragment extends Fragment {

    ChannelAdapter mAdapter;
    ArrayList<String> tabNamesList;
    ArrayList<Fragment> fragments;

    @BindView(R.id.tab_indicator)
    TabPageIndicator tabIndicator;
    @BindView(R.id.vp)
    ViewPager vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_news, null);

        ButterKnife.bind(this, view);

        tabNamesList = new ArrayList<>();
        fragments = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            tabNamesList.add("哈哈" + i);
            fragments.add(new HomePageFragment());
        }

        mAdapter = new ChannelAdapter(getFragmentManager(), tabNamesList, fragments);
        vp.setAdapter(mAdapter);

        tabIndicator.setViewPager(vp);
        tabIndicator.setVisibility(View.VISIBLE);


        String baseApi = "http://bdapp2.bandao.cn/bandao/api_4_0_0";

//        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(baseApi).build();
//        IMoonService moonService = restAdapter.create(IMoonService.class);
//        moonService.getChannels(new CustomApiCallback<BaseArrayModel<ChannelModel>>() {
//            @Override
//            public void success(BaseArrayModel<ChannelModel> channelModels, Response response) {
//
//            }
//
//        });

        return view;
    }

}
