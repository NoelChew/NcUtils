package com.noelchew.ncutils.email.sparkpost;

import com.google.gson.Gson;

/**
 * Created by noelchew on 4/27/16.
 */
public class SparkPostResultWrapper {
    private SparkPostResult results;

    public SparkPostResultWrapper(SparkPostResult results) {
        this.results = results;
    }

    public SparkPostResult getResults() {
        return results;
    }

    public static SparkPostResultWrapper fromJson(String json) {
        return new Gson().fromJson(json, SparkPostResultWrapper.class);
    }
}
