package com.sha.viewbadger;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

class ViewSetup {

    private BadgeView view;
    private BadgeParams params;

    ViewSetup(BadgeView view, BadgeParams params) {
        this.view = view;
        this.params = params;
    }

    void setup() {
        LinearLayout container = container();
        ViewGroup.LayoutParams lp = params.target.getLayoutParams();

        int index = params.targetParent.indexOfChild(params.target);

        params.targetParent.removeView(params.target);
        params.targetParent.addView(container, index, lp);

        container.addView(params.target);

        container.addView(view);

        params.targetParent.invalidate();
    }

    private LinearLayout container() {
        LinearLayout container = new LinearLayout(params.context);
        container.setGravity(Gravity.CENTER_VERTICAL);
        return container;
    }

}
