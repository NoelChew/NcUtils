//package com.noelchew.ncutils;
//
///**
// * Created by noelchew on 11/20/15.
// */
//
//import android.content.Context;
//
///**
// * Created by noelchew on 8/27/15.
// */
//public class AnalyticsUtil {
//    public static void sendAnalyticsScreenTrack(Context context, String screen) {
//        if (BuildConfig.DEBUG) {
//            return;
//        }
//        sendGoogleAnalyticsScreenTrack(context, screen);
//        sendAnswersAnalyticsScreenTrack(screen);
//    }
//
//    public static void sendAnalyticsEventTrack(Context context, String category, String event) {
//        if (BuildConfig.DEBUG) {
//            return;
//        }
//        sendGoogleAnalyticsEventTrack(context, category, event);
//        sendAnswersAnalyticsEventTrack(category, event);
//    }
//
//    // --------- Google Analytics - Begin --------- //
//    private static void sendGoogleAnalyticsScreenTrack(Context context, String screen) {
////        Tracker tracker = null;
////        if (context instanceof Activity) {
////            tracker = ((MyApplication) ((Activity) context).getApplication()).getDefaultTracker();
////        } else if (context instanceof FragmentActivity) {
////            tracker = ((MyApplication) ((FragmentActivity) context).getApplication()).getDefaultTracker();
////        }
////        HitBuilders.ScreenViewBuilder builder = new HitBuilders.ScreenViewBuilder();
////        tracker.setScreenName(screen);
////        tracker.send(builder.build());
//    }
//
//    private static void sendGoogleAnalyticsEventTrack(Context context, String category, String event) {
////        Tracker tracker = null;
////        if (context instanceof Activity) {
////            tracker = ((MyApplication) ((Activity) context).getApplication()).getDefaultTracker();
////        } else if (context instanceof FragmentActivity) {
////            tracker = ((MyApplication) ((FragmentActivity) context).getApplication()).getDefaultTracker();
////        }
////        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
////                .setCategory(category)
////                .setAction(event);
////        tracker.send(builder.build());
//
//    }
//    // --------- Google Analytics - End --------- //
//
//
//    // --------- Answers Analytics - Begin --------- //
//    private static void sendAnswersAnalyticsScreenTrack(String screen) {
////        Answers.getInstance().logContentView(new ContentViewEvent()
////                .putContentName(screen)
////                .putContentType("Screens"));
//    }
//
//
//    private static void sendAnswersAnalyticsEventTrack(String category, String event) {
////        Answers.getInstance().logCustom(new CustomEvent(event)
////                .putCustomAttribute("Category", category));
//    }
//
//    // --------- Answers Analytics - End --------- //
//}
