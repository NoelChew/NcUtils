package com.noelchew.ncutils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.noelchew.ncutils.email.EmailListener;
import com.noelchew.ncutils.email.EmailUtil;
import com.noelchew.ncutils.email.sparkpost.SparkPostEmailUtil;
import com.noelchew.ncutils.email.sparkpost.SparkPostRecipient;
import com.noelchew.ncutils.email.sparkpost.SparkPostSender;

/**
 * Created by noelchew on 11/23/15.
 */
public class AppSettingsUtil {

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

    public static void feedback(final Context context, final String sparkPostApiKey, final String appName, final String emailAddress, final ProgressDialog progressDialog) {
        feedback(context, sparkPostApiKey, appName, emailAddress, R.string.nc_utils_feedback, progressDialog);
    }

    public static void feedbackWithBadRating(final Context context, final String sparkPostApiKey, final String appName, final String emailAddress, final ProgressDialog progressDialog) {
        feedback(context, sparkPostApiKey, appName, emailAddress, R.string.nc_utils_rate_dialog_message_3, progressDialog);
    }

    public static void feedback(final Context context, final String sparkPostApiKey, final String appName, final String emailAddress, int dialogMessageResourceId, final ProgressDialog progressDialog) {
        progressDialog.setTitle(R.string.ncutils_loading);
        progressDialog.setMessage(context.getString(R.string.ncutils_please_wait));
        final CharSequence[] feedbackType = {
                context.getString(R.string.nc_utils_feedback_anonymously),
                context.getString(R.string.nc_utils_feedback_by_email),
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle(dialogMessageResourceId)
                .setItems(feedbackType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (feedbackType[which].toString().compareToIgnoreCase(context.getString(R.string.nc_utils_feedback_anonymously)) == 0) {
                            sendFeedbackAnonymously(context, sparkPostApiKey, appName, emailAddress, progressDialog);
                        } else {
                            sendFeedbackByEmail(context, appName, emailAddress);
                        }
                    }
                })
                .create()
                .show();
    }

    public static void sendFeedbackAnonymously(final Context context, final String sparkPostApiKey, final String appName, final String emailAddress, final ProgressDialog progressDialog) {
        AlertDialogUtil.showAlertDialogWithInput(context,
                context.getString(R.string.nc_utils_feedback),
                context.getString(R.string.nc_utils_feedback_message),
                context.getString(R.string.nc_utils_insert_feedback),
                "",
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE,
                new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence feedback) {
                        final String feedbackContent = feedback.toString();
                        if (!TextUtils.isEmpty(feedbackContent)) {
                            AlertDialogUtil.showAlertDialogWithInput(context,
                                    context.getString(R.string.nc_utils_feedback_input_email_address_title),
                                    context.getString(R.string.nc_utils_feedback_input_email_address_message),
                                    context.getString(R.string.nc_utils_feedback_hint_email_address),
                                    "",
                                    InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS | InputType.TYPE_CLASS_TEXT,
                                    new MaterialDialog.InputCallback() {
                                        @Override
                                        public void onInput(@NonNull MaterialDialog dialog, CharSequence email) {
                                            if (progressDialog != null && !progressDialog.isShowing()) {
                                                progressDialog.show();
                                            }

                                            final String userEmail = email.toString();

                                            EmailListener emailListener = new EmailListener() {
                                                @Override
                                                public void onSuccess() {
                                                    if (progressDialog != null && progressDialog.isShowing()) {
                                                        progressDialog.dismiss();
                                                    }
                                                    AlertDialogUtil.showAlertDialogMessage(context, R.string.nc_utils_feedback, R.string.nc_utils_feedback_send_success);
                                                    AnalyticsUtil.sendAnalyticsEventTrack(context, "Action", "Send Feedback Anonymously");
                                                }

                                                @Override
                                                public void onError(String errorMessage) {
                                                    if (progressDialog != null && progressDialog.isShowing()) {
                                                        progressDialog.dismiss();
                                                    }
                                                    if (!TextUtils.isEmpty(errorMessage)) {
                                                        ToastUtil.toastShortMessage(context, errorMessage);
                                                    }
                                                    AnalyticsUtil.sendAnalyticsEventTrack(context, "Error", "Error Sending Feedback Anonymously");
                                                }
                                            };

                                            if (ValidatorUtil.isValidEmail(userEmail.trim())) {
                                                SparkPostEmailUtil.sendEmail(context,
                                                        sparkPostApiKey,
                                                        appName + " Android App - Feedback",
                                                        feedbackContent + "\nUser Email: " + userEmail.trim(),
                                                        new SparkPostSender("abc@noelchew.com", "Unknown user"),
                                                        new SparkPostRecipient(emailAddress),
                                                        emailListener);
                                            } else {
                                                SparkPostEmailUtil.sendEmail(context,
                                                        sparkPostApiKey,
                                                        appName + " Android App - Feedback",
                                                        feedbackContent + "\nUser Email: " + userEmail.trim(),
                                                        new SparkPostSender("abc@noelchew.com", "Unknown user"),
                                                        new SparkPostRecipient(emailAddress),
                                                        emailListener);
                                            }
                                        }
                                    },
                                    context.getString(R.string.nc_utils_feedback_send));
                        } else {
                            ToastUtil.toastShortMessage(context, R.string.nc_utils_feedback_invalid_feedback);
                        }
                    }
                },
                context.getString(R.string.ncutils_ok),
                context.getString(R.string.nc_utils_feedback_cancel),
                new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }
        );
    }

    public static void sendFeedbackByEmail(Context context, String appName, String
            emailAddress) {
        EmailUtil.sendEmailByIntent(context, appName + " " + context.getString(R.string.nc_utils_feedback_email_subject), context.getString(R.string.nc_utils_feedback_email_message), emailAddress);
        AnalyticsUtil.sendAnalyticsEventTrack(context, "Action", "Feedback by Email");
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