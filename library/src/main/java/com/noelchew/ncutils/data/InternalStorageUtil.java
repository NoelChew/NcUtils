package com.noelchew.ncutils.data;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by noelchew on 8/4/15.
 */
public class InternalStorageUtil {

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    public static String getString(Context context, String key) {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    context.openFileInput(key)));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
            return removeLastNewLine(stringBuffer.toString());
        } catch (FileNotFoundException e1) {
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String removeLastNewLine(String input) {
        if (input.length() >= 1 && input.contains("\n")) {
            if (input.substring(input.length() - 1, input.length()).compareTo("\n") == 0) {
                return input.substring(0, input.length() - 1);
            }
        }
        return input;
    }

    public static void setString(Context context, String key, String value) {
        try {
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            fos.write(value.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        String value = getString(context, key);
        if (value.compareTo(TRUE) != 0 && value.compareTo(FALSE) != 0) {
            return defValue;
        } else {
            return Boolean.valueOf(value);
        }
    }

    public static void setBoolean(Context context, String key, boolean value) {
        if (value) {
            setString(context, key, TRUE);
        } else {
            setString(context, key, FALSE);
        }
    }

    public static int getInt(Context context, String key) {
        String value = getString(context, key);

        if (TextUtils.isDigitsOnly(value)) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    public static void setInt(Context context, String key, int value) {
        setString(context, key, String.valueOf(value));
    }

}