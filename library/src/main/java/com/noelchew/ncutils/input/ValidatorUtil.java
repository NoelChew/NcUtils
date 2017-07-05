package com.noelchew.ncutils.input;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by noelchew on 7/22/16.
 */
public class ValidatorUtil {
    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
