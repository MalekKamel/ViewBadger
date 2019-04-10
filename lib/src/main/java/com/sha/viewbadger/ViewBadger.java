package com.sha.viewbadger;

import android.content.Context;
import android.view.View;
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

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) target.getChildAt(0);
        BottomNavigationItemView menuItemView = (BottomNavigationItemView) menuView.getChildAt(targetTabIndex);

        BadgeParams params = new BadgeParams()
                .setTargetType(BadgeParams.TargetType.BOTTOM_NAV)
                .setContext(context)
                .setTarget(menuView)
                .setTargetParent(menuItemView)
                .setBottomNavView(menuView)
                .setBottomNavItemView(menuItemView)
                .setTargetTabIndex(targetTabIndex)
                .setBottomNav(target);

        return  new BadgeView(params);
    }

    public BadgeView setupWithView(
            View view,
            Context context
    ){
        if (!(view.getParent() instanceof ViewGroup))
            throw new IllegalArgumentException("the parent of the target: " + view + " must be a ViewGroup");

        BadgeParams params = new BadgeParams()
                .setTargetType(BadgeParams.TargetType.VIEW)
                .setContext(context)
                .setTarget(view)
                .setTargetParent((ViewGroup) view.getParent());

        return  new BadgeView(params);
    }

}
