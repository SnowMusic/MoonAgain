package com.zrx.moonagain.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.zrx.moonagain.R;
import com.zrx.moonagain.helpers.ExoplayerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlayActivity extends AppCompatActivity {

    @BindView(R.id.player)
    SimpleExoPlayerView playerView;

    String path = "http://og3ie8mqp.bkt.clouddn.com/videotest.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);




    }


}
