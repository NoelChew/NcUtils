package com.noelchew.ncutils.email;

/**
 * Created by noelchew on 7/22/16.
 */
public interface EmailListener {
    void onSuccess();
    void onError(String errorMessage);
}
