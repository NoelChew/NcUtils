package com.noelchew.ncutils.alert;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by noelchew on 7/16/15.
 */
public class ToastUtil {

    public static void toastShortMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastShortMessage(Context context, int messageResource) {
        Toast.makeText(context, messageResource, Toast.LENGTH_SHORT).show();
    }

    public static void toastLongMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void toastLongMessage(Context context, int messageResource) {
        Toast.makeText(context, messageResource, Toast.LENGTH_LONG).show();
    }
}
