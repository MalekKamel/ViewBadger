package com.sha.viewbadger;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

class BottomNavSetup {

    private BadgeView view;
    private BadgeParams params;

    public BottomNavSetup(BadgeView view, BadgeParams params) {
        this.view = view;
        this.params = params;
    }

    void setup() {
//        params.bottomNavItemView.removeViewAt(0);
        //////

//        View tabView = LayoutInflaterUtil.inflate(R.layout.bottomnav_badge, params.context);
//        FrameLayout wBadge = tabView.findViewById(R.id.w_badge);
//        ImageView ivTabIcon = tabView.findViewById(R.id.iv_tabIcon);
//
//        Menu menu = params.bottomNav.getMenu();
//
//        ivTabIcon.setImageDrawable(menu.getItem(params.targetTabIndex).getIcon());
////
////        FrameLayout customView = new FrameLayout(params.context);
////
////        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
////                ViewGroup.LayoutParams.MATCH_PARENT,
////                ViewGroup.LayoutParams.MATCH_PARENT);
////
////        p.gravity = Gravity.CENTER;
////        customView.setLayoutParams(p);
////
//        params.bottomNavItemView.addView(tabView);
////
////        customView.addView(createTabIcon(tab),
////                0,
////                new LinearLayout.LayoutParams(
////                        PixelUtil.from(24, params.context),
////                        PixelUtil.from(24, params.context),
////                        1
////                )
////        );
//        wBadge.addView(view,
//                new FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        1
//                )
//        );

        params.bottomNavItemView.addView(
                view,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        );
    }

}
