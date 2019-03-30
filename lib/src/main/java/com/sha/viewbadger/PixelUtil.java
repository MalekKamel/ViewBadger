package com.sha.viewbadger;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class PixelUtil {

    public static int from(int dip, Context context){
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
        return (int) px;
    }
}
