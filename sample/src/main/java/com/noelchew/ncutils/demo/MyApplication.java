package com.noelchew.ncutils.demo;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.noelchew.ncutils.database.DatabaseModule;
import com.noelchew.ncutils.demo.model.DummyObject;

/**
 * Created by noelchew on 7/4/16.
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

        DatabaseModule.init(getApplicationContext(), 1, new DummyObject());
    }
}
