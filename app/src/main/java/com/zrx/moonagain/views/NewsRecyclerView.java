package com.zrx.moonagain.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrx.moonagain.R;
import com.zrx.moonagain.utils.IntentUtils;
import com.zrx.snowlibrary.utils.DensityUtil;
import com.zrx.snowlibrary.views.Banner;

import java.util.ArrayList;

/**
 * 新闻列表
 * Created by Schnee on 2017/3/7.
 */

public class NewsRecyclerView extends RecyclerView {

    Context context;

    public NewsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(linearLayoutManager);

        setAdapter(new NewsAdapter());

    }

    public void setData(ArrayList<String> datas) {

    }

    private class NewsAdapter extends RecyclerView.Adapter {

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;
            } else return 1;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.vh_news, null);
                return new NewsViewHolder(itemView);
            } else {
                Banner banner = new Banner(context);
                banner.getLayoutParams().height = DensityUtil.dp2px(context, 160);
                ArrayList<String> a = new ArrayList<>();
                a.add("http://img5.imgtn.bdimg.com/it/u=3577875476,238353561&fm=23&gp=0.jpg");
                a.add("http://img3.imgtn.bdimg.com/it/u=1497822951,4264477876&fm=23&gp=0.jpg");
                a.add("http://img5.imgtn.bdimg.com/it/u=3577875476,238353561&fm=23&gp=0.jpg");
                a.add("http://img3.imgtn.bdimg.com/it/u=1497822951,4264477876&fm=23&gp=0.jpg");
                banner.setData(a);
                return new BannerViewHolder(banner);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(IntentUtils.toNewsDetailActivity(context));
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class NewsViewHolder extends ViewHolder {

        TextView tv_title;
        ImageView iv_news_cover;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_news_cover = (ImageView) itemView.findViewById(R.id.iv_news_cover);

        }
    }

    private class BannerViewHolder extends ViewHolder {

        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }

}
