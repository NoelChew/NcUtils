package com.noelchew.ncutils.others;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by noelchew on 06/01/2018.
 */

public class EmailUtil {

    public static void sendEmailByIntent(Context context, String subject, String message, String recipientEmail) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
        context.startActivity(Intent.createChooser(emailIntent, "Email:"));
    }

}
