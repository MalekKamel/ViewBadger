package com.sha.viewbadger;

import android.content.Context;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ViewBadger {

    public BadgeView setupWithTabLayout(
            TabLayout tabLayout,
            int targetTabIndex,
            TabLayoutMode mode,
            Context context
    ){

        TabLayout.Tab tab = tabLayout.getTabAt(targetTabIndex);

        BadgeParams params = new BadgeParams()
                .setTargetType(BadgeParams.TargetType.TAB_LAYOUT)
                .setContext(context)
                .setTabLayout(tabLayout)
                .setTarget(tab.view)
                .setTargetTabIndex(targetTabIndex)
                .setTargetParent(tab.view)
                .setTabLayoutMode(mode);

        return  new BadgeView(params);
    }

    public BadgeView setupWithViewBottomNavigation(
            BottomNavigationView target,
            int targetTabIndex,
            Context context
    ){
        if (!(target.getParent() instanceof ViewGroup))
            throw new IllegalArgumentException("the parent of the target: " + target + " must be a ViewGroup");

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) target.getChildAt(0);
        BottomNavigationItemView menuItemView = (BottomNavigationItemView) menuView.getChildAt(targetTabIndex);

        BadgeParams params = new BadgeParams()
                .setTargetType(BadgeParams.TargetType.BOTTOM_NAV)
                .setContext(context)
                .setTarget(menuItemView)
                .setTargetParent(menuItemView);

        return  new BadgeView(params);
    }

}
