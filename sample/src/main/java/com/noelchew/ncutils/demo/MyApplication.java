package com.noelchew.ncutils.demo;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;

/**
 * Created by aio-synergy on 7/4/16.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    private static MyApplication mInstance = null;

    public synchronized static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new MaterialCommunityModule())
                .with(new MaterialModule());

        mInstance = this;
    }
}
