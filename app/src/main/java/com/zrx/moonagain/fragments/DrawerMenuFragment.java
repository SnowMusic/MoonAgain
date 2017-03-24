package com.zrx.moonagain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zrx.moonagain.MoonBaseFragment;
import com.zrx.moonagain.R;
import com.zrx.moonagain.dto.DailyNewsListModel;
import com.zrx.moonagain.views.MenuHeaderView;
import com.zrx.snowlibrary.utils.ListUtil;
import com.zrx.snowlibrary.utils.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 抽屉界面
 * Created by Schnee on 2017/3/22.
 */

public class DrawerMenuFragment extends MoonBaseFragment {

    @BindView(R.id.rcl_menu)
    RecyclerView rclMenu;
    @BindView(R.id.header_view)
    MenuHeaderView headerView;

    ArrayList<DailyNewsListModel.OthersBean> others = new ArrayList<>();
    MenuAdapter adapter;
    ArrayList<Integer> followThemesId = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View menuView = LayoutInflater.from(getActivity()).inflate(R.layout.frg_drawer_menu, null);
        ButterKnife.bind(this, menuView);

        adapter = new MenuAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rclMenu.setLayoutManager(manager);
        rclMenu.setAdapter(adapter);
        //recyclerview 的child和adapter中的child粘附时机有区别
        rclMenu.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if (selectedIndex == -1) {
                    changeSelectedStatus(0);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });
        return menuView;
    }

    public void setData(DailyNewsListModel model, OnDrawerMenuClickListener menuClickListener) {
        if (ListUtil.isEmpty(model.getOthers())) return;
        this.onDrawerMenuClickListener = menuClickListener;
        others.clear();
        others.addAll(model.getOthers());
        adapter.notifyDataSetChanged();
    }

    private int selectedIndex = -1;

    private void changeSelectedStatus(int position) {
        if (position == selectedIndex) return;
        if (selectedIndex != -1)
            rclMenu.getChildAt(selectedIndex).setSelected(false);
        rclMenu.getChildAt(position).setSelected(true);
        if (position == 0) onDrawerMenuClickListener.onClick(null);
        else
            onDrawerMenuClickListener.onClick(others.get(position).getId());
        selectedIndex = position;

    }

    OnDrawerMenuClickListener onDrawerMenuClickListener;

    private class MenuAdapter extends RecyclerView.Adapter {

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                return new TitleVH(LayoutInflater.from(getActivity()).inflate(R.layout.vh_drawer_menu_tv_home_page, null));
            } else
                return new MenuVH(LayoutInflater.from(getActivity()).inflate(R.layout.vh_menu_items, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
            if (position != 0) {
                MenuVH holder = (MenuVH) viewHolder;
//            //Todo
//            for (int i = 0; i < followThemesId.size(); i++) {
//                int id = others.get(position).getId();
//                if (id == followThemesId.get(i))
//                    holder.iv_arrow.setImageResource(R.drawable.menu_follow);
//                else holder.iv_arrow.setImageResource(R.drawable.menu_arrow);
//            }
                holder.tv_theme.setText(StringUtils.nullToEmpty(others.get(position - 1).getName()));

                holder.iv_arrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshFollowStatus(position);
                    }

                });
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeSelectedStatus(position);

                }
            });
        }

        @Override
        public int getItemCount() {
            if (ListUtil.isNotEmpty(others))
                return others.size() + 1;
            else return 0;
        }
    }

    //follow某个专题
    private void refreshFollowStatus(int position) {
        followThemesId.add(others.get(position).getId());
        adapter.notifyDataSetChanged();
    }

    class MenuVH extends RecyclerView.ViewHolder {

        TextView tv_theme;
        ImageView iv_arrow;

        public MenuVH(View itemView) {
            super(itemView);
            tv_theme = (TextView) itemView.findViewById(R.id.tv_theme);
            iv_arrow = (ImageView) itemView.findViewById(R.id.iv_arrow);
        }
    }

    class TitleVH extends RecyclerView.ViewHolder {

        public TitleVH(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    public interface OnDrawerMenuClickListener {
        void onClick(Integer id);
    }
}
