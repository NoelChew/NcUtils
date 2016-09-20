package com.noelchew.ncutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;

public class SharedPreferencesUtil {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, "");
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key, defValue);
    }

    public static int getInt(Context context, String key) {
        return getSharedPreferences(context).getInt(key, 0);
    }

    public static void setString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).commit();
    }

    public static void setBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    public static void setInt(Context context, String key, int value) {
        getSharedPreferences(context).edit().putInt(key, value).commit();
    }

    public static HashMap<String, String> getHashMap(Context context, String key) {
        String serialisedMap = getString(context, key);
        if (!TextUtils.isEmpty(serialisedMap)) {
            return new HashMap<>(HashMapUtil.stringToMap(serialisedMap));
        } else {
            return new HashMap<>();
        }
    }

    public static void setHashMap(Context context, String key, HashMap<String, String> hashMap) {
        String serialisedMap = HashMapUtil.mapToString(hashMap);
        setString(context, key, serialisedMap);
    }
}
