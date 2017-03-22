package com.zrx.moonagain.fragments;

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
import com.zrx.moonagain.dto.DailyNewsListModel;
import com.zrx.moonagain.views.MenuHeaderView;
import com.zrx.snowlibrary.utils.ClickUtil;
import com.zrx.snowlibrary.utils.ListUtil;
import com.zrx.snowlibrary.utils.StringUtils;
import com.zrx.snowlibrary.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Schnee on 2017/3/22.
 */

public class DrawerMenuFragment extends MoonBaseFragment {

    @BindView(R.id.rcl_menu)
    RecyclerView rclMenu;
    @BindView(R.id.header_view)
    MenuHeaderView headerView;
    @BindView(R.id.tv_themes_header)
    TextView tv_themes_header;

    ArrayList<DailyNewsListModel.OthersBean> others = new ArrayList<>();
    MenuAdapter adapter;
    ArrayList<Integer> followThemesId = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View menuView = LayoutInflater.from(getActivity()).inflate(R.layout.frg_drawer_menu, null);
        ButterKnife.bind(this, menuView);

        adapter = new MenuAdapter();
        rclMenu = (RecyclerView) menuView.findViewById(R.id.rcl_menu);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rclMenu.setLayoutManager(manager);
        rclMenu.setAdapter(adapter);
        tv_themes_header.setSelected(true);
        return menuView;
    }

    public void setData(DailyNewsListModel model) {
        if (ListUtil.isEmpty(model.getOthers())) return;
        others.clear();
        others.addAll(model.getOthers());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_themes_header)
    public void onClick() {
        if (ClickUtil.isFastClick()) return;
        changeSelectedStatus(-1);
    }

    private int selectedIndex = -1;

    private void changeSelectedStatus(int position) {
        if (position == selectedIndex) return;
        if (selectedIndex == -1) {
            tv_themes_header.setSelected(false);
        } else rclMenu.getChildAt(selectedIndex).setSelected(false);

        if (position == -1) tv_themes_header.setSelected(true);
        else rclMenu.getChildAt(position).setSelected(true);
        selectedIndex = position;
    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuVH> {

        @Override
        public MenuVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.vh_menu_items, null);
            return new MenuVH(itemView);
        }

        @Override
        public void onBindViewHolder(MenuVH holder, final int position) {
    //Todo
            for (int i = 0; i < followThemesId.size(); i++) {
                int id = others.get(position).getId();
                if (id == followThemesId.get(i))
                    holder.iv_arrow.setImageResource(R.drawable.menu_follow);
                else holder.iv_arrow.setImageResource(R.drawable.menu_arrow);
            }
            holder.tv_theme.setText(StringUtils.nullToEmpty(others.get(position).getName()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(getActivity(), "" + position);
                    changeSelectedStatus(position);

                }
            });

            holder.iv_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshFollowStatus(position);
                }

            });

        }

        @Override
        public int getItemCount() {
            return others.size();
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

}
