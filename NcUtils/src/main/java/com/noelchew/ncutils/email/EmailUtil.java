package com.noelchew.ncutils.email;

import android.content.Context;
import android.content.Intent;

/**
 * Created by noelchew on 11/23/15.
 */
public class EmailUtil {
    public static void sendEmailByIntent(Context context, String subject, String message, String recipientEmail) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{recipientEmail});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        context.startActivity(Intent.createChooser(emailIntent, "Email:"));
    }
}