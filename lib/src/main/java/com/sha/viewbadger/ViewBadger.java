package com.sha.viewbadger;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ViewBadger {

    /**
     *
     * @param tabLayout
     * @param targetTabIndex
     * @param mode
     * @param context
     * @return
     */
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

    /**
     *
     * @param target
     * @param targetTabIndex
     * @param context
     * @return
     */
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

    /**
     *
     * @param view the target view that badge will be displayed on
     * @param isViewRound this is used to optimize the location of the badge on
     *                    the view
     * @param rootViewId the top view id in the XML hierarchy, it's used to disable clipping
     *                   children to allow showing badge outside the target view nounds
     * @param context object
     * @return badge
     */
    public BadgeView setupWithView(
            View view,
            int rootViewId,
            boolean isViewRound,
            Context context
    ){
        if (!(view.getParent() instanceof ViewGroup))
            throw new IllegalArgumentException("the parent of the target: " + view + " must be a ViewGroup");

        BadgeParams params = new BadgeParams()
                .setTargetType(BadgeParams.TargetType.VIEW)
                .setContext(context)
                .setTarget(view)
                .setTargetParent((ViewGroup) view.getParent())
                .setViewRound(isViewRound)
                .setRootViewId(rootViewId);

        return  new BadgeView(params);
    }

}
