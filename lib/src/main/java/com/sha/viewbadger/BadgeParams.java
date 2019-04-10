package com.sha.viewbadger;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class BadgeParams {

    public enum TargetType {
        TAB_LAYOUT,
        BOTTOM_NAV,
        VIEW
    }
    public TargetType targetType;
    public Context context;
    public View target;
    public int tabIndex;
    public int targetTabIndex;
    public TabLayout tabLayout;
    public BottomNavigationView bottomNav;
    public ViewGroup targetParent;
    public TabLayoutMode tabLayoutMode;
    public BottomNavigationMenuView bottomNavView;
    public BottomNavigationItemView bottomNavItemView;


    public BadgeParams setContext(Context context) {
        this.context = context;
        return this;
    }

    public BadgeParams setTarget(View target) {
        this.target = target;
        return this;
    }

    public BadgeParams setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
        return this;
    }

    public BadgeParams setTargetTabIndex(int targetTabIndex) {
        this.targetTabIndex = targetTabIndex;
        return this;
    }

    public BadgeParams setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
        return this;
    }

    public BadgeParams setBottomNav(BottomNavigationView bottomNav) {
        this.bottomNav = bottomNav;
        return this;
    }

    public BadgeParams setTargetType(TargetType targetType) {
        this.targetType = targetType;
        return this;
    }

    public BadgeParams setTargetParent(ViewGroup targetParent) {
        this.targetParent = targetParent;
        return this;
    }

    public BadgeParams setTabLayoutMode(TabLayoutMode tabLayoutMode) {
        this.tabLayoutMode = tabLayoutMode;
        return this;
    }

    public BadgeParams setBottomNavView(BottomNavigationMenuView bottomNavView) {
        this.bottomNavView = bottomNavView;
        return this;
    }

    public BadgeParams setBottomNavItemView(BottomNavigationItemView bottomNavItemView) {
        this.bottomNavItemView = bottomNavItemView;
        return this;
    }
}
 