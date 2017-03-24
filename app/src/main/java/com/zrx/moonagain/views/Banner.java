package com.zrx.moonagain.views;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.zrx.moonagain.dto.LatestNewsModel;
import com.zrx.snowlibrary.R;
import com.zrx.snowlibrary.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义banner
 * Created by Schnee on 2017/3/7.
 */

public class Banner extends RelativeLayout {

    Context ctx;
    ViewPager viewpager;
    RadioGroup indicator;

    ArrayList<LatestNewsModel.StoriesBean> beanList = new ArrayList<>();


    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        LayoutInflater.from(context).inflate(R.layout.banner, this);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (RadioGroup) findViewById(R.id.indicator);
        viewpager.setAdapter(new BannerAdapter());

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(currentIndex, 3000);
                buttons.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    ArrayList<RadioButton> buttons = new ArrayList<>();
    ArrayList<BannerContentView> imageViews = new ArrayList<>();

    public void setData(List<LatestNewsModel.StoriesBean> storiesBeanList) {
        if (ListUtil.isEmpty(storiesBeanList)) return;

        beanList.clear();
        beanList.addAll(storiesBeanList);

        viewpager.getAdapter().notifyDataSetChanged();
        buttons.clear();
        imageViews.clear();
        for (int i = 0; i < beanList.size(); i++) {
            RadioButton button = radioButton();
            buttons.add(button);
            indicator.addView(button);
        }
        buttons.get(0).setChecked(true);
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    int currentIndex = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == beanList.size() - 1) {
                viewpager.setCurrentItem(0);
            } else {
                viewpager.setCurrentItem(currentIndex + 1);
            }
        }
    };

    private RadioButton radioButton() {
        RadioButton radioButton = new RadioButton(ctx);
        radioButton.setClickable(false);
        radioButton.setButtonDrawable(R.drawable.banner_indicator);
        radioButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return radioButton;
    }

    private class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BannerContentView bannerContentView = new BannerContentView(ctx);
            bannerContentView.setData(beanList.get(position).getTitle(), beanList.get(position).getImage());
            imageViews.add(bannerContentView);
            container.addView(bannerContentView);
            return bannerContentView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }

}
