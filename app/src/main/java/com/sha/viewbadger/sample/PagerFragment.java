package com.sha.viewbadger.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sha.viewbadger.BadgeView;
import com.sha.viewbadger.TabLayoutMode;
import com.sha.viewbadger.ViewBadger;

public class PagerFragment extends Fragment {

    public String title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BadgeView badge;
    private int index;
    private EditText etText;

    public static PagerFragment newInstance(
            String title,
            ViewPager viewPager,
            TabLayout tabLayout,
            int index) {
        PagerFragment fragment = new PagerFragment();
        fragment.title = title;
        fragment.viewPager = viewPager;
        fragment.tabLayout = tabLayout;
        fragment.index = index;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_pager,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi();
        setupBadgeWithView();
        setupBadgeWithTabLayout();
    }

    private void setupBadgeWithView() {
        BadgeView badge = new ViewBadger().setupWithView(
                getView().findViewById(R.id.iv),
                getContext()
        );

        badge.setText("33");
        badge.setOnClickListener(v -> viewPager.setCurrentItem(index));
        badge.show(true);
    }

    private void setupBadgeWithTabLayout() {
        if (badge != null) return;

        badge = new ViewBadger().setupWithTabLayout(
                tabLayout,
                index,
                TabLayoutMode.WITH_ICON,
                getContext()
        );

        badge.setText("33");
        badge.setOnClickListener(v -> viewPager.setCurrentItem(index));
        badge.show(true);
    }

    private void setupUi() {
        etText = getView().findViewById(R.id.et_text);
        getView().findViewById(R.id.btn_changeColor).setOnClickListener(v -> badge.setBadgeBackgroundColor(Color.RED));
        getView().findViewById(R.id.btn_changeText).setOnClickListener(v -> badge.setText(etText.getText().toString()));
        getView().findViewById(R.id.btn_show).setOnClickListener(v -> badge.show());
        getView().findViewById(R.id.btn_hide).setOnClickListener(v -> badge.hide());
        getView().findViewById(R.id.btn_noTrim).setOnClickListener(v -> badge.setTrimNumberAfter99(!badge.isTrimNumberAfter99()));

    }


}
