package com.noelchew.ncutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by NoelChew on 3/14/16.
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

    public static String getStringFromResourceName(Context context, String resourceName) {
        return context.getString(getResourceId(context, resourceName));
    }
}
