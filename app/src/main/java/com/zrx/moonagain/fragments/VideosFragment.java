package com.zrx.moonagain.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zrx.moonagain.R;
import com.zrx.moonagain.activities.VideoPlayActivity;
import com.zrx.snowlibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Schnee on 2017/2/17.
 */

public class VideosFragment extends Fragment {

    @BindView(R.id.rcl_container)
    RecyclerView rclContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frg_video, null);
        ButterKnife.bind(this, view);

        rclContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rclContainer.setAdapter(new VideoAdapter());

        return view;
    }

    String src = "";

    private class VideoAdapter extends RecyclerView.Adapter<VideoVH> {


        @Override
        public VideoVH onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView iv = new ImageView(getActivity());
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            iv.setImageResource(R.mipmap.ic_launcher);
            return new VideoVH(iv);
        }

        @Override
        public void onBindViewHolder(VideoVH holder, final int position) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), VideoPlayActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    class VideoVH extends RecyclerView.ViewHolder {

        public VideoVH(View itemView) {
            super(itemView);
        }


    }

}
