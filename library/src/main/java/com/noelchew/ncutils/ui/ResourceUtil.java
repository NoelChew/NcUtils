package com.noelchew.ncutils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.noelchew.ncutils.R;

/**
 * Created by noelchew on 3/14/16.
 */
public class ResourceUtil {
    public static Bitmap drawableResourceToBitmap(Context context, int drawableResource) {
        return BitmapFactory.decodeResource(context.getResources(), drawableResource);
    }

    public static Drawable getDrawableFromResourceId(Context context, int drawableResourceId) {
        return ContextCompat.getDrawable(context, drawableResourceId);
    }

    public static int getDrawableResourceId(Context context, String drawableName) {
        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }

    public static Drawable getDrawableFromResourceName(Context context, String drawableName) {
        return getDrawableFromResourceId(context, getDrawableResourceId(context, drawableName));
    }

    public static int getResourceId(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "id", context.getPackageName());
    }

    public static int getStringResourceId(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "string", context.getPackageName());
    }

    public static String getStringFromResourceName(Context context, String resourceName) {
        return context.getString(getStringResourceId(context, resourceName));
    }

    public static int getPrimaryColor(Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
        return value.data;
    }

    public static int getPrimaryColorDark(Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, value, true);
        return value.data;
    }

    public static int getAccentColor(Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }

    public static int getPrimaryTextColor(Context context) {
        return getColorFromAttr(context, android.R.attr.textColorPrimary);
    }

    public static int getSecondaryTextColor(Context context) {
        return getColorFromAttr(context, android.R.attr.textColorSecondary);
    }

    public static int getColor(Context context, int colorResourceId) {
        return ContextCompat.getColor(context, colorResourceId);
    }

    @SuppressLint("ResourceAsColor")
    public static int getColorFromAttr(Context context, @AttrRes int attrColor) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrColor, typedValue, true);
        if (typedValue.resourceId != 0) {
            return ContextCompat.getColor(context, typedValue.resourceId);
        } else {
            return ContextCompat.getColor(context, typedValue.data);
        }
    }

    public static int getScreenWidthPixel(AppCompatActivity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeightPixel(AppCompatActivity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
