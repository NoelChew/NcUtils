package com.noelchew.ncutils.location;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import com.mypopsy.maps.StaticMap;
import com.noelchew.ncutils.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by noelchew on 8/17/16.
 */
public class GoogleMapUtil {

    private static int DEFAULT_IMAGE_WIDTH = 200;
    private static int DEFAULT_IMAGE_HEIGHT = 120;
    private static int DEFAULT_ZOOM = 14;
    private static boolean DEFAULT_WITH_MARKER = true;

    public static String getMapImageUrl(String latitude, String longitude) {
        return getMapImageUrl(Double.parseDouble(latitude), Double.parseDouble(longitude), DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT, DEFAULT_ZOOM, DEFAULT_WITH_MARKER);
    }

    public static String getMapImageUrl(String latitude, String longitude, int imageWidth, int imageHeight, int zoom, boolean withMarker) {
        return getMapImageUrl(Double.parseDouble(latitude), Double.parseDouble(longitude), imageWidth, imageHeight, zoom, withMarker);
    }

    public static String getMapImageUrl(double latitude, double longitude) {
        return getMapImageUrl(latitude, longitude, DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT, DEFAULT_ZOOM, DEFAULT_WITH_MARKER);
    }

    public static String getMapImageUrl(double latitude, double longitude, int imageWidth, int imageHeight, int zoom, boolean withMarker) {
        StaticMap staticMap = new StaticMap().center(new StaticMap.GeoPoint(latitude, longitude))
                .type(StaticMap.Type.ROADMAP)
                .size(imageWidth, imageHeight)
                .zoom(zoom);

        if (withMarker) {
            staticMap.marker(new StaticMap.GeoPoint(latitude, longitude));
        }
        return staticMap.toString();
    }

    public static void startGetDirectionActivity(Context context, double latitude, double longitude) {
        startGetDirectionActivity(context, String.valueOf(latitude), String.valueOf(longitude));
    }

    public static void startGetDirectionActivity(Context context, double latitude, double longitude, String placeId) {
        startGetDirectionActivity(context, String.valueOf(latitude), String.valueOf(longitude), placeId);
    }

    public static void startGetDirectionActivity(Context context, String latitude, String longitude) {
        startGetDirectionActivity(context, latitude, longitude, "");
    }

    public static void startGetDirectionActivity(Context context, String latitude, String longitude, String googlePlaceId) {
        String uriGoogle = "https://www.google.com/maps/dir/?api=1&destination=" + latitude + "," + longitude;
        if (!TextUtils.isEmpty(googlePlaceId)) {
            uriGoogle += "&destination_place_id=" + googlePlaceId;
        }
        Intent intentGoogleNav = new Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle));
        intentGoogleNav.setPackage("com.google.android.apps.maps");

        boolean userHasWaze = isWazeInstalled(context);
        if (userHasWaze) {
            String url = "waze://?ll=" + latitude + ", " + longitude + "&navigate=yes";
            Intent intentWaze = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intentWaze.setPackage("com.waze");

            String title = " ";
            Intent chooserIntent = Intent.createChooser(intentGoogleNav, title);
            Intent[] arr = new Intent[1];
            arr[0] = intentWaze;
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arr);
            context.startActivity(chooserIntent);
        } else {
            context.startActivity(intentGoogleNav);
        }
    }

    public static void startGetDirectionActivity(Context context, String locationName) {
        Uri uriGoogle = Uri.parse("geo:0,0?q=" + Uri.encode(locationName));
        Intent intentGoogleNav = new Intent(Intent.ACTION_VIEW, uriGoogle);
        intentGoogleNav.setPackage("com.google.android.apps.maps");

        boolean userHasWaze = isWazeInstalled(context);
        if (userHasWaze) {
            Uri uriWaze = Uri.parse("https://waze.com/ul?q=" + Uri.encode(locationName) + "&navigate=yes");
            Intent intentWaze = new Intent(Intent.ACTION_VIEW, uriWaze);
            intentWaze.setPackage("com.waze");

            String title = " ";
            Intent chooserIntent = Intent.createChooser(intentGoogleNav, title);
            Intent[] arr = new Intent[1];
            arr[0] = intentWaze;
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arr);
            context.startActivity(chooserIntent);
        } else {
            context.startActivity(intentGoogleNav);
        }
    }

    public static void startShowLocationActivity(Context context, double latitude, double longitude) {
        startShowLocationActivity(context, String.valueOf(latitude), String.valueOf(longitude), "");
    }

    public static void startShowLocationActivity(Context context, double latitude, double longitude, String label) {
        startShowLocationActivity(context, String.valueOf(latitude), String.valueOf(longitude), label);
    }

    public static void startShowLocationActivity(Context context, String latitude, String longitude) {
        startShowLocationActivity(context, latitude, longitude, "");
    }

    public static void startShowLocationActivity(Context context, String latitude, String longitude, String label) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String _label = "";
        if (!TextUtils.isEmpty(label)) {
            try {
                _label = "(" + URLEncoder.encode(label, "UTF-8") + ")";
            } catch (UnsupportedEncodingException e) {
                _label = "";
            }
        }
        intent.setData(Uri.parse("https://maps.google.com/maps?q=loc:" + latitude + "," + longitude + _label));

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.ncutils_view_location)));
    }

    public static boolean isGoogleMapsInstalled(Context context) {
        try {
            context.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isWazeInstalled(Context context) {
        try {
            context.getPackageManager().getApplicationInfo("com.waze", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
