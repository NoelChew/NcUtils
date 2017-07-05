package com.noelchew.ncutils.others;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by noelchew on 11/23/15.
 */
public class PlayMarketUtil {

    public static void goToMarket(Context context, String webUrl, String marketUri) {
        Uri uri = Uri.parse(marketUri);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(webUrl)));
        }
    }

    public static void rateUs(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static void shareApp(Context context) {
        //create our intent with a action of ACTION_SEND
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //we want to send a simple 'text' message
        sendIntent.setType("text/plain");
        //this is the text we are sending
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I find this app useful and cool! You should give it a try too!\n" + "http://play.google.com/store/apps/details?id=" + context.getPackageName() + "&referrer=utm_source%3D" + context.getPackageName());
        //ask android to show apps that can handle this intent
        context.startActivity(Intent.createChooser(sendIntent, "Share via:"));
    }
}