package com.zrx.moonagain.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zrx.moonagain.MoonBaseFragment;
import com.zrx.moonagain.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Schnee on 2017/3/23.
 */

public class NewsListFragment extends MoonBaseFragment {

    Context context;
//    @BindView(R.id.rcl_news_list)
//    NewsRecyclerView rclNewsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = LayoutInflater.from(context).inflate(R.layout.frg_news_list, null);
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));

        return view;
    }
}
