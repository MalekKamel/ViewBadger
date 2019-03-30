package com.sha.viewbadger;

import android.widget.FrameLayout;

class ViewSetup {

    private BadgeView view;
    private BadgeParams params;

    ViewSetup(BadgeView view, BadgeParams params) {
        this.view = view;
        this.params = params;
    }

    void setup() {
        FrameLayout container = new FrameLayout(params.context);

        int indexOfTarget = params.targetParent.indexOfChild(params.target);

        params.targetParent.removeView(params.target);
        params.targetParent.addView(container, indexOfTarget, params.target.getLayoutParams());

        container.addView(params.target);
        container.addView(view);

        params.targetParent.invalidate();
    }

}
