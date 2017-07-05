package com.noelchew.ncutils.ui;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by noelchew on 20/09/2016.
 */
public class PixelUtil {

    // https://developer.android.com/guide/practices/screens_support.html#dips-pels

    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static int pxToDp(Context context, float px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((px / displayMetrics.density) + 0.5);
    }

}
