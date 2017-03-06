package com.zrx.moonagain.helpers;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.zrx.moonagain.MoonApplication;
import com.zrx.moonagain.R;

/**
 * Created by Schnee on 2017/2/17.
 */

public class ExoplayerHelper {

    private Context context ;
    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;

    private String path;

    private ExoplayerHelper instance;


    public ExoplayerHelper getInstance() {
        if (instance == null) {
            context = MoonApplication.instance;

        }
        return instance;
    }

    public ExoplayerHelper(SimpleExoPlayerView playerView) {
        this.playerView = playerView;
        initPlayer();
        preparePlayer();
    }

    private void preparePlayer() {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.getString(R.string.app_name)), bandwidthMeter);
        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(path), dataSourceFactory, extractorsFactory, null, null);// Prepare the player with the source.
        player.prepare(videoSource);
    }

    private void initPlayer() {
        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();
        // 3. Create the player
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        playerView.setPlayer(player);

    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPlayerView(SimpleExoPlayerView playerView) {
        this.playerView = playerView;

    }

    public void changePlayerView(SimpleExoPlayerView toPlayerView) {

    }

    public void onRelease() {
        player.release();
    }

    public void onPause() {

    }

    public void seekTo(int seconds) {
        player.seekTo(seconds * 1000);
    }

}
