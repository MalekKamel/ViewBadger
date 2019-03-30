package com.sha.viewbadger;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * Created by Sha on 1/9/18.
 */

public final class LayoutInflaterUtil {

    @SuppressWarnings("unchecked")
    public static <T> T inflate(int res, Context context){
        return (T) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(res, null);
    }
}
