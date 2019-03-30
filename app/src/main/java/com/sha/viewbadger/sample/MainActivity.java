package com.sha.viewbadger.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.sha.viewbadger.BadgeView;
import com.sha.viewbadger.ViewBadger;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUi();
        setupPager();
        setupBadgeWithBottomNavigation();
    }

    private void setupUi() {
        bottomNavigationView = findViewById(R.id.bottomNav);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setupPager() {
        List<PagerFragment> fragments = Arrays.asList(
                PagerFragment.newInstance("Flight", viewPager, tabLayout, 0),
                PagerFragment.newInstance("Filter", viewPager, tabLayout, 1),
                PagerFragment.newInstance("FAQ", viewPager, tabLayout, 2)
        );
        AppPagerAdapter adapter = new AppPagerAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_flight_land);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_filter);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_faq);
    }


    private void setupBadgeWithBottomNavigation() {
        BadgeView badge = new ViewBadger().setupWithViewBottomNavigation(
                bottomNavigationView,
                1,
                this
        );

        badge.setText("1111");
        badge.show(true);
    }


}
