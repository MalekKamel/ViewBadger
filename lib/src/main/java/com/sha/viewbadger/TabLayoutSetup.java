package com.sha.viewbadger;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

class TabLayoutSetup {

    private BadgeView view;
    private BadgeParams params;

    public TabLayoutSetup(BadgeView view, BadgeParams params) {
        this.view = view;
        this.params = params;
        setStaticTabLayoutHeight();
    }

    private void setStaticTabLayoutHeight() {
        int height = 0;

        switch (params.tabLayoutMode) {
            case WITH_TITLE:
            case WITH_ICON:
                height = 48;

                break;

            case WITH_TITLE_AND_ICON:
                height = 72;
                break;
        }

        ViewGroup.LayoutParams p = params.tabLayout.getLayoutParams();
        p.height = PixelUtil.from(height, params.context);
        params.tabLayout.setLayoutParams(p);
    }

    void setup() {

        TabLayout.Tab tab = params.tabLayout.getTabAt(params.targetTabIndex);

        if (tab == null) return;

        switch (params.tabLayoutMode) {
            case WITH_TITLE:
                withTitle(tab);
                break;

            case WITH_ICON:
                withIcon(tab);
                break;

            case WITH_TITLE_AND_ICON:
                withIconAndTitle(tab);
                break;
        }

        if (params.tabLayout.getTabMode() == TabLayout.MODE_SCROLLABLE){
            tab.getCustomView().setLayoutParams(
                    new LinearLayout.LayoutParams(
                            PixelUtil.from(100, params.context),
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            1
                    )
            );
        }
    }

    private void withTitle(TabLayout.Tab tab) {

        LinearLayout customView = createCustomView();

        customView.setOrientation(LinearLayout.HORIZONTAL);
        tab.setCustomView(customView);

        customView.setGravity(Gravity.CENTER);

        customView.addView(createTitleView(tab),
                0,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                )
        );
        customView.addView(view,
                1,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                )
        );
    }

    private void withIcon(TabLayout.Tab tab) {

        View tabView = LayoutInflaterUtil.inflate(R.layout.tablayout_badge, params.context);
        FrameLayout wBadge = tabView.findViewById(R.id.w_badge);
        ImageView ivTabIcon = tabView.findViewById(R.id.iv_tabIcon);
        ivTabIcon.setImageDrawable(tab.getIcon());

        tab.setCustomView(tabView);

        wBadge.addView(view,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1
                )
        );
    }

    private void withIconAndTitle(TabLayout.Tab tab) {
        LinearLayout customView = createCustomView();

        customView.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout iconAndTitleContainer = new LinearLayout(params.context);
        iconAndTitleContainer.setOrientation(LinearLayout.VERTICAL);
        iconAndTitleContainer.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
                );
        p.gravity = Gravity.FILL;
        iconAndTitleContainer.setLayoutParams(p);

        tab.setCustomView(customView);

        iconAndTitleContainer.addView(createTabIcon(tab),
                0,
                new LinearLayout.LayoutParams(
                        PixelUtil.from(24, params.context),
                        PixelUtil.from(24, params.context),
                        1
                )
        );
        iconAndTitleContainer.addView(createTitleView(tab),
                1,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        customView.addView(iconAndTitleContainer,
                0,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1
                )
        );

        customView.addView(view,
                1,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1.0f
                )
        );

    }

    private TextView createTitleView(TabLayout.Tab tab) {
        TextView tv = new TextView(params.context);
        tv.setText(tab.getText());
        tv.setTextColor(params.tabLayout.getTabTextColors());
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setSelected(tab.isSelected());
        params.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab t) {
                if (t == tab) return;
                // When we add the TextView to a selected tab
                // and user selects another tab, it remains selected.
                // So we update the selection here
                tv.setSelected(false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab t) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab t) {

            }
        });

        return tv;
    }

    private ImageView createTabIcon(TabLayout.Tab tab) {
        ImageView icon = LayoutInflaterUtil.inflate(R.layout.design_layout_tab_icon, params.context);

        icon.setImageDrawable(tab.getIcon());

        return icon;
    }

    private LinearLayout createCustomView() {
        LinearLayout customView = new LinearLayout(params.context);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        p.gravity = Gravity.CENTER;
        customView.setLayoutParams(p);
        return customView;
    }
}
//