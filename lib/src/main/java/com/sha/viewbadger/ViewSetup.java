package com.sha.viewbadger;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.core.view.ViewCompat;

class ViewSetup {

    private BadgeView badge;
    private BadgeParams params;
    private static final float TRANSLATION = 30;
    int containerId = ViewCompat.generateViewId();

    ViewSetup(BadgeView badge, BadgeParams params) {
        this.badge = badge;
        this.params = params;
    }

    void setup() {
        FrameLayout container = new FrameLayout(params.context);
        container.setId(containerId);
        container.setBackgroundColor(Color.parseColor("#242323"));

        int indexOfTarget = params.targetParent.indexOfChild(params.target);

        params.targetParent.removeView(params.target);

        params.targetParent.addView(container, indexOfTarget);

        container.addView(params.target);
        container.addView(badge);

        params.targetParent.invalidate();

        handleTranslation();

        disableClip(container);
    }

    private void handleTranslation() {
        if (params.isViewRound && params.target.getLayoutParams().width > 150) return;

        float x = 0;
        float y = 0;

        switch (params.position) {
            case CENTER:
                x = 0;
                y = 0;
                break;

            case TOP_END:
                x = TRANSLATION;
                y = -TRANSLATION;
                break;

            case TOP_START:
                x = -TRANSLATION;
                y = -TRANSLATION;
                break;

            case BOTTOM_END:
                x = TRANSLATION;
                y = TRANSLATION;
                break;

            case BOTTOM_START:
                x = -TRANSLATION;
                y = TRANSLATION;
                break;

            case CENTER_VERTICAL:
                x = -TRANSLATION;
                y = 0;
                break;

            case CENTER_HORIZONTAL:
                x = 0;
                y = -TRANSLATION;
                break;

        }

        if (x != 0)
            badge.animate()
                    .translationXBy(x)
                    .withStartAction(() -> badge.setVisibility(View.GONE))
                    .withEndAction(() -> badge.setVisibility(View.VISIBLE));
        if (y != 0)
            badge.animate()
                    .translationYBy(y)
                    .withStartAction(() -> badge.setVisibility(View.GONE))
                    .withEndAction(() -> badge.setVisibility(View.VISIBLE));
    }

    private void disableClip(View view) {
//        if (view.getParent() == null) {
//            return;
//        }
//
//        if (view instanceof ViewGroup) {
//            ((ViewGroup) view).setClipChildren(false);
//            return;
//        }
//
//        if (view.getParent() instanceof View) {
//            disableClip((View) view.getParent());
//        }

//        if (view.getId() == android.R.id.content) return;

        if (!(view.getParent() instanceof ViewGroup)) return;

        ViewGroup parent = (ViewGroup) view.getParent();
        parent.setClipChildren(false);
        parent.setClipToPadding(false);

        if (view.getId() == params.rootViewId) return;

        if (parent.getParent() != null)
            disableClip(parent);
    }

}
