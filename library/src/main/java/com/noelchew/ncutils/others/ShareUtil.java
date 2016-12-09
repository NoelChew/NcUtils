package com.noelchew.ncutils.others;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.noelchew.ncutils.R;

/**
 * Created by noelchew on 09/12/2016.
 */

public class ShareUtil {

    public static void shareText(Context context, String text) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.ncutils_share)));
    }

    // make sure Uri is created using FileProvider
    public static void shareImage(Context context, Uri imageUri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.ncutils_share)));
    }
}
