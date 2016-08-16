package com.noelchew.ncutils.email.mandrill;

import android.content.Context;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by noelchew on 11/23/15.
 */
public class MandrillEmailUtil {

    public static void sendEmail(Context context, String apiKey, String subject, String message, MandrillSender sender, MandrillRecipient recipient, FutureCallback<String> callback) {
        ArrayList<MandrillRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        sendEmail(context, apiKey, subject, message, sender, recipients, callback);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, MandrillRecipient recipient, FutureCallback<String> callback) {
        MandrillSender sender = new MandrillSender("unknown@email.com", "unknown user");
        ArrayList<MandrillRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        sendEmail(context, apiKey, subject, message, sender, recipients, callback);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, String recipientEmail, FutureCallback<String> callback) {
        MandrillRecipient recipient1 = new MandrillRecipient(recipientEmail, "");
        sendEmail(context, apiKey, subject, message, recipient1, callback);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, ArrayList<MandrillRecipient> recipients, FutureCallback<String> callback) {
        MandrillSender sender = new MandrillSender("unknown@email.com", "unknown user");
        sendEmail(context, apiKey, subject, message, sender, recipients, callback);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, MandrillSender sender, ArrayList<MandrillRecipient> recipients, final FutureCallback<String> callback) {
        MandrillEmailJsonRequest mandrillEmailJsonRequest = new MandrillEmailJsonRequest(apiKey, subject, message, recipients, sender);
        Ion.with(context)
                .load(MandrillEmailJsonRequest.API_BASE_URL + MandrillEmailJsonRequest.EMAIL_API_PATH)
                .setStringBody(mandrillEmailJsonRequest.toString())
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        JSONArray object = null;
                        try {
                            object = new JSONArray(result);
                            JSONObject object1 = object.getJSONObject(0);
                            if (object1.get("status").toString().compareToIgnoreCase("sent") == 0) {
                                // success
                                callback.onCompleted(e, result);
                            } else {
                                callback.onCompleted(e, null);
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            callback.onCompleted(e, null);
                        }
                    }
                });

//        FutureCallback<String> callback = new FutureCallback<String>() {
//            @Override
//            public void onCompleted(Exception e, String result) {
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//
//                if (result != null) {
//                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
//                    builder.setTitle(R.string.fragment_title_feedback)
//                            .setMessage(R.string.feedback_send_success)
//                            .create()
//                            .show();
//                } else {
//                    ToastUtil.toastShortMessage(context, e.getMessage());
//                    Log.e(TAG, e.getMessage());
//                }
//            }
//        };
    }
}
