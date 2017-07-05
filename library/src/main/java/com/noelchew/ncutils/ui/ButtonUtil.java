package com.noelchew.ncutils.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by noelchew on 09/12/2016.
 */

public class ButtonUtil {

    public static void setSideBoarder(Context context, View view, int color, int stroke, int left, int top, int right, int bottom) {
        GradientDrawable border = new GradientDrawable();
        border.setStroke(stroke, ContextCompat.getColor(context, color));
        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, left, top, right, bottom);
        view.setBackground(layerDrawable);
    }

    public static void setRadiusColor(Context context, View view, int radiusDp, int colorNormal, int colorPressed, int colorDisabled) {
        int radiusPx = PixelUtil.dpToPx(context, radiusDp);

        GradientDrawable drawableNormal = new GradientDrawable();
        drawableNormal.setCornerRadius(radiusPx);
        drawableNormal.setColor(ContextCompat.getColor(context, colorNormal));

        GradientDrawable drawablePressed = new GradientDrawable();
        drawablePressed.setCornerRadius(radiusPx);
        drawablePressed.setColor(ContextCompat.getColor(context, colorPressed));

        GradientDrawable drawableDisabled = new GradientDrawable();
        drawableDisabled.setCornerRadius(radiusPx);
        drawableDisabled.setColor(ContextCompat.getColor(context, colorDisabled));

        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[] { -android.R.attr.state_enabled }, drawableDisabled);
        sld.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, drawablePressed);
        sld.addState(new int[] { -android.R.attr.state_pressed, android.R.attr.state_enabled }, drawableNormal);
        view.setBackground(sld);
    }
}
