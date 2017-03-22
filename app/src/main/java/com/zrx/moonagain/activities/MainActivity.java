package com.zrx.moonagain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrx.moonagain.R;
import com.zrx.moonagain.StarBaseAcitivity;
import com.zrx.moonagain.dto.DailyNewsListModel;
import com.zrx.moonagain.dto.VersionModel;
import com.zrx.moonagain.fragments.DrawerMenuFragment;
import com.zrx.moonagain.fragments.NewsFragment;
import com.zrx.moonagain.fragments.WeatherFragment;
import com.zrx.moonagain.helpers.UserHelper;
import com.zrx.moonagain.interfaces.CustomApiCallback;
import com.zrx.moonagain.utils.ApiManager;
import com.zrx.moonagain.utils.IntentUtils;
import com.zrx.snowlibrary.utils.ClickUtil;
import com.zrx.snowlibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends StarBaseAcitivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.layout_container)
    FrameLayout container;

    DrawerMenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initFragments();
        initData();
    }

    private void initData() {

        Call<DailyNewsListModel> dailyNewsListModelCall = ApiManager.getMoonService().getThemes();
        dailyNewsListModelCall.enqueue(new CustomApiCallback<DailyNewsListModel>(){
            @Override
            public void onResponse(Call<DailyNewsListModel> call, Response<DailyNewsListModel> response) {
                super.onResponse(call, response);
                if (response!=null) {
                    menuFragment.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<DailyNewsListModel> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    NewsFragment newsFragment;
    WeatherFragment weatherFragment;
    Fragment[] menuFragments = {newsFragment, weatherFragment};

    private void initFragments() {
        menuFragments[0] = new NewsFragment();
        menuFragments[1] = new WeatherFragment();
        selectFragment(defaultChannelIndex);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        menuFragment = (DrawerMenuFragment) getSupportFragmentManager().findFragmentById(R.id.frg_menu);
    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (ClickUtil.canFinish()) finish();
            else ToastUtils.showToast(this, "再按一次退出");
//            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
//            getFragmentManager().beginTransaction().add(newsFragment, "newsFragment").commit();
            startActivity(new Intent(MainActivity.this, SplashActivity.class));
//            selectFragment(0);
        } else if (id == R.id.nav_gallery) {
//            getFragmentManager().beginTransaction().add(weatherFragment, "weatherFragment").commit();
            selectFragment(1);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    int currentIndex = -1;
    int defaultChannelIndex = 0;// 默认选中的栏目

    private void selectFragment(int index) {
        if (currentIndex == index) return;
        if (index == defaultChannelIndex && !menuFragments[defaultChannelIndex].isAdded()) {
            switchFragment(null, menuFragments[defaultChannelIndex]);
        } else
            switchFragment(menuFragments[currentIndex], menuFragments[index]);
        currentIndex = index;
    }

    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (to.isAdded()) {
            transaction.show(to);
        } else {
            transaction.add(R.id.layout_container, to);
        }
        if (from != null)
            transaction.hide(from);
        transaction.commit();

    }

}
