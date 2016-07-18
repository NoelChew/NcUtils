package com.noelchew.ncutils.email.sparkpost;

import android.content.Context;
import android.text.TextUtils;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by noelchew on 11/23/15.
 */
public class SparkPostEmailUtil {
    public static void sendEmail(Context context, String apiKey, String subject, String message, SparkPostSender sender, SparkPostRecipient recipient, FutureCallback<String> listener) {
        ArrayList<SparkPostRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        sendEmail(context, apiKey, subject, message, sender, recipients, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, SparkPostRecipient recipient, FutureCallback<String> listener) {
        SparkPostSender sender = new SparkPostSender("feedback@sparkpostbox.com", "unknown user");
        ArrayList<SparkPostRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);
        sendEmail(context, apiKey, subject, message, sender, recipients, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, String recipientEmail, FutureCallback<String> listener) {
        // need to verify sending domain first
        SparkPostRecipient recipient1 = new SparkPostRecipient("feedback@sparkpostbox.com");
        sendEmail(context, apiKey, subject, message, recipient1, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, ArrayList<SparkPostRecipient> recipients, FutureCallback<String> listener) {
        SparkPostSender sender = new SparkPostSender("feedback@sparkpostbox.com", "unknown user");
        sendEmail(context, apiKey, subject, message, sender, recipients, listener);
    }

    public static void sendEmail(Context context, String apiKey, String subject, String message, SparkPostSender sender, ArrayList<SparkPostRecipient> recipients, final FutureCallback<String> callback) {
        SparkPostEmailJsonRequest sparkPostEmailJsonRequest = new SparkPostEmailJsonRequest(subject, message, recipients, sender);
        Ion.with(context)
                .load(SparkPostEmailJsonRequest.API_BASE_URL + SparkPostEmailJsonRequest.EMAIL_API_PATH)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", apiKey)
                .setStringBody(sparkPostEmailJsonRequest.toString())
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            if (!TextUtils.isEmpty(result)) {
                                SparkPostResultWrapper requestResult = SparkPostResultWrapper.fromJson(result);
                                if (requestResult.getResults().getTotal_rejected_recipients() == 0) {
                                    callback.onCompleted(e, result);
                                    return;
                                }
                            }
                            callback.onCompleted(e, null);

                        } catch (Exception e1) {
                            e1.printStackTrace();
                            callback.onCompleted(e, null);
                        }
                    }
                });
    }

}
