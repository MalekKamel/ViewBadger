package com.sha.viewbadger;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

class ViewSetup {

    private BadgeView badge;
    private BadgeParams params;
    private static final float TRANSLATION = 30;

    ViewSetup(BadgeView badge, BadgeParams params) {
        this.badge = badge;
        this.params = params;
    }

    void setup() {

        params.badgeTarget.addView(badge);
        badge.bringToFront();

        // set elevation to show the badge above FloatingActionButton
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            badge.setElevation(20);
        }

        params.badgeTarget.bringChildToFront(badge);

        handleTranslation();

        disableClip(params.badgeTarget);
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

        if (!(view.getParent() instanceof ViewGroup)) return;

        ViewGroup parent = (ViewGroup) view.getParent();
        parent.setClipChildren(false);
        parent.setClipToPadding(false);

        if (view.getId() == params.rootViewId) return;

        if (parent.getParent() != null)
            disableClip(parent);
    }

    static BadgeTarget target(View view) {

       if (view.getParent() == null) return null;

       if (!(view.getParent() instanceof View)) return null;

        View parent = (View) view.getParent();

        if (parent instanceof BadgeTarget)
            return (BadgeTarget) parent;

        return target(parent);
    }

}
