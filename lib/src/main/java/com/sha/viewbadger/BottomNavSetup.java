package com.sha.viewbadger;

import android.view.ViewGroup;

class BottomNavSetup {

    private BadgeView view;
    private BadgeParams params;

    public BottomNavSetup(BadgeView view, BadgeParams params) {
        this.view = view;
        this.params = params;
    }

    void setup() {
        params.targetParent.addView(view,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
    }

}
