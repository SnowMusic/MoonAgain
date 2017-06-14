package com.zrx.moonagain.activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.zrx.moonagain.MoonApplication;
import com.zrx.moonagain.R;
import com.zrx.moonagain.StarBaseAcitivity;
import com.zrx.moonagain.dto.DailyNewsListModel;
import com.zrx.moonagain.dto.ThemeNewsModel;
import com.zrx.moonagain.fragments.DrawerMenuFragment;
import com.zrx.moonagain.fragments.HomePageFragment;
import com.zrx.moonagain.fragments.NewsFragment;
import com.zrx.moonagain.fragments.WeatherFragment;
import com.zrx.moonagain.interfaces.CustomApiCallback;
import com.zrx.moonagain.utils.ApiManager;
import com.zrx.snowlibrary.utils.ClickUtil;
import com.zrx.snowlibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends StarBaseAcitivity implements View.OnClickListener {

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
        //获取专题列表
        Call<DailyNewsListModel> dailyNewsListModelCall = ApiManager.getMoonService().getThemes();
        dailyNewsListModelCall.enqueue(new CustomApiCallback<DailyNewsListModel>() {
            @Override
            public void onResponse(Call<DailyNewsListModel> call, Response<DailyNewsListModel> response) {
                super.onResponse(call, response);
                if (response != null) {
                    menuFragment.setData(response.body(), onDrawerMenuClickListener);
                }
            }

            @Override
            public void onFailure(Call<DailyNewsListModel> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    public static boolean hasPermission(String permissionType) {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(MoonApplication.instance, permissionType);
        return hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasAppPermission(String... permissions) {
        for (String p : permissions) {
            if (!hasPermission(p)) return false;
        }
        return true;
    }

    public static void getPermission(Activity aty, int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(aty, permissions, requestCode);
    }

    public static final int SHARE_REQUEST_CODE = 111;

    public static boolean needGetSharePermission(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            if (hasAppPermission(mPermissionList)) {
                return false;
            } else {
                getPermission(context, SHARE_REQUEST_CODE, mPermissionList);
                return true;
            }
        }
        return false;
    }

    DrawerMenuFragment.OnDrawerMenuClickListener onDrawerMenuClickListener = new DrawerMenuFragment.OnDrawerMenuClickListener() {
        @Override
        public void onClick(Integer id) {
            drawer.closeDrawers();
            if ( !needGetSharePermission(MainActivity.this)){
                ToastUtils.showToast(MainActivity.this,"权限已经获取");
            }


//            if (id == null) {
//                switchFragment(null, homePageFragment);
//            } else {
//                Call<ThemeNewsModel> themeNewsModelCall = ApiManager.getMoonService().getThemeNews(id);
//                themeNewsModelCall.enqueue(new CustomApiCallback<ThemeNewsModel>() {
//
//                });
//            }
        }
    };

    HomePageFragment homePageFragment;

    private void initFragments() {
        homePageFragment = new HomePageFragment();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SHARE_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            ToastUtils.showToast(MainActivity.this,"权限已经获取");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    int currentIndex = -1;
    int defaultChannelIndex = 0;// 默认选中的栏目

    private void selectFragment(int index) {
        if (currentIndex == index) return;
        if (index == defaultChannelIndex) {
            switchFragment(null, menuFragment);
        }
//        else
//            switchFragment(menuFragments[currentIndex], menuFragments[index]);
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
