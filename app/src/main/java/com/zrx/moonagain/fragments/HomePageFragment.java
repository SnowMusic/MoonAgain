package com.zrx.moonagain.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrx.moonagain.MoonBaseFragment;
import com.zrx.moonagain.R;
import com.zrx.moonagain.dto.LatestNewsModel;
import com.zrx.moonagain.interfaces.CustomApiCallback;
import com.zrx.moonagain.utils.ApiManager;
import com.zrx.moonagain.utils.IntentUtils;
import com.zrx.moonagain.views.Banner;
import com.zrx.snowlibrary.utils.DensityUtil;
import com.zrx.snowlibrary.utils.DisplayPictureUtil;
import com.zrx.snowlibrary.utils.ListUtil;
import com.zrx.snowlibrary.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Schnee on 2017/3/6.
 */

public class HomePageFragment extends MoonBaseFragment {

    @BindView(R.id.rv_newslist)
    RecyclerView rvNewslist;

    HomeNewsAdapter adapter;
    Context context;

    //    ArrayList<>
    LatestNewsModel latestNewsModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_home_page, null);
        ButterKnife.bind(this, view);

        this.context = getActivity();
        adapter = new HomeNewsAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewslist.setLayoutManager(manager);
        rvNewslist.setAdapter(adapter);


        Call<LatestNewsModel> latestNews = ApiManager.getMoonService().getLatestNews();
        latestNews.enqueue(new CustomApiCallback<LatestNewsModel>() {
            @Override
            public void onResponse(Call<LatestNewsModel> call, Response<LatestNewsModel> response) {
                super.onResponse(call, response);
                latestNewsModel = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LatestNewsModel> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
        return view;
    }

    private class HomeNewsAdapter extends RecyclerView.Adapter {

        final int TYPE_BANNER = 0;
        final int TYPE_TAG = 1;
        final int TYPE_NEWS = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = null;
            if (viewType == TYPE_BANNER) {
                Banner banner = new Banner(context);
                banner.getLayoutParams().height = DensityUtil.dp2px(context, 240);
                banner.setData(latestNewsModel.getTop_stories());
                return new BannerViewHolder(banner);
            } else if (viewType == TYPE_TAG) {
                itemView = LayoutInflater.from(context).inflate(R.layout.vh_tags, null);
                return new TagViewHolder(itemView);
            } else if (viewType == TYPE_NEWS) {
                itemView = LayoutInflater.from(context).inflate(R.layout.vh_news, null);
                return new NewsViewHolder(itemView);
            } else return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position > 1) {
                final LatestNewsModel.StoriesBean storiesBean = latestNewsModel.getStories().get(position - 2);
                NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
                newsViewHolder.tv_title.setText(StringUtils.nullToEmpty(storiesBean.getTitle()));
                DisplayPictureUtil.showPicture(getActivity(), newsViewHolder.iv_news_cover, storiesBean.getImages().get(0));
                newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(IntentUtils.toNewsDetailActivity(context, storiesBean.getId()));
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (latestNewsModel != null) {
                if (ListUtil.isNotEmpty(latestNewsModel.getTop_stories())) count = 1;

                if (ListUtil.isNotEmpty(latestNewsModel.getStories()))
                    count += latestNewsModel.getStories().size() + 1;
            }
            return count;
        }

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return TYPE_BANNER;
                case 1:
                    return TYPE_TAG;
            }
            return TYPE_NEWS;
        }
    }

    private class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView iv_news_cover;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_news_cover = (ImageView) itemView.findViewById(R.id.iv_news_cover);
        }
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class TagViewHolder extends RecyclerView.ViewHolder {

        public TagViewHolder(View itemView) {
            super(itemView);
        }
    }
}
