package com.noelchew.ncutils;

/**
 * Created by noelchew on 3/22/16.
 */
public class GoogleMapUtil {

    public static String getMapImageUrl(double latitude, double longitude) {
        return getMapImageUrl(String.valueOf(latitude), String.valueOf(longitude));
    }

    public static String getMapImageUrl(String latitude, String longitude) {
        return "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=14&size=200x120&maptype=normal";
    }
}
