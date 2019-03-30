package com.sha.viewbadger;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class AnimUtil {

    static Animation fadeOut() {
        return anim(new AccelerateInterpolator());
    }

    static Animation fadeIn() {
        return anim(new DecelerateInterpolator());
    }

    static Animation anim(Interpolator interpolator) {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setInterpolator(interpolator);
        animation.setDuration(200);
        return animation;
    }

}
