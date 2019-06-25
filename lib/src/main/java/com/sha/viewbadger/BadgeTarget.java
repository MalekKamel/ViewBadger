package com.sha.viewbadger;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class BadgeTarget extends FrameLayout {

    public BadgeTarget(@NonNull Context context) {
        super(context);
    }

    public BadgeTarget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BadgeTarget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BadgeTarget(
            @NonNull Context context,
            @Nullable AttributeSet attrs,
            int defStyleAttr,
            int defStyleRes
    ) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
