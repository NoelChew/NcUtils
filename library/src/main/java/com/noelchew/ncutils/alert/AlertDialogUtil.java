package com.noelchew.ncutils.alert;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.noelchew.ncutils.R;
import com.noelchew.ncutils.ui.ResourceUtil;

import java.util.List;

/**
 * Created by noelchew on 09/11/2016.
 */

public class AlertDialogUtil {

    public static void showAlertDialogMessage(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .create()
                .show();
    }

    public static void showAlertDialogMessage(Context context, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .create()
                .show();
    }

    public static void showAlertDialogMessage(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

    public static void showAlertDialogMessage(Context context, int title, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

    public static void showAlertDialogMessage(Context context, String title, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showAlertDialogMessage(Context context, int title, int message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showAlertDialogMessage(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showAlertDialogMessage(Context context, int message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showAlertDialogWithInput(Context context, String title, String message, String hint, String prefill, int inputType, final InputCallback inputCallback, String positiveButtonText) {
        final View dialogView = View.inflate(context, R.layout.dialog_input, null);
        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
        editText.setHint(hint);
        editText.setText(prefill);
        editText.setInputType(inputType);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputCallback.onInput(dialog, ((EditText) dialogView.findViewById(R.id.edit_text)).getText());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showAlertDialogWithInput(Context context, String title, String message, String hint, String prefill, int inputType, final InputCallback inputCallback, String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener negativeListener) {
        final View dialogView = View.inflate(context, R.layout.dialog_input, null);
        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
        editText.setHint(hint);
        editText.setText(prefill);
        editText.setInputType(inputType);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputCallback.onInput(dialog, ((EditText) dialogView.findViewById(R.id.edit_text)).getText());
                    }
                })
                .setNegativeButton(negativeButtonText, negativeListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showAlertDialogWithSelections(Context context, int title, List<String> selectionList, DialogInterface.OnClickListener onClickListener) {
        showAlertDialogWithSelections(context, context.getString(title), selectionList, onClickListener);
    }

    public static void showAlertDialogWithSelections(Context context, String title, List<String> selectionList, DialogInterface.OnClickListener onClickListener) {
        CharSequence[] tmpSelections = new CharSequence[selectionList.size()];
        for (int i = 0; i < selectionList.size(); i++) {
            tmpSelections[i] = selectionList.get(i);
        }

        final CharSequence[] selections = tmpSelections;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setItems(selections, onClickListener)
                .create()
                .show();
    }


    public static void showAlertDialogWithSelections(Context context, List<String> selectionList, DialogInterface.OnClickListener onClickListener) {
        CharSequence[] tmpSelections = new CharSequence[selectionList.size()];
        for (int i = 0; i < selectionList.size(); i++) {
            tmpSelections[i] = selectionList.get(i);
        }

        final CharSequence[] selections = tmpSelections;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(selections, onClickListener)
                .create()
                .show();
    }

    public static void showYesNoDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.ncutils_yes, positiveListener)
                .setNegativeButton(R.string.ncutils_no, null)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showYesNoDialog(Context context, int title, int message, int positiveButtonText, int negativeButtonText, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveListener)
                .setNegativeButton(negativeButtonText, negativeListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showYesNoDialog(Context context, String title, String message, String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveListener)
                .setNegativeButton(negativeButtonText, negativeListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }


    public static void showYesNoDialog(Context context, int title, int positiveButtonText, int negativeButtonText, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveListener)
                .setNegativeButton(negativeButtonText, negativeListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showYesNoDialog(Context context, String title, String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveListener)
                .setNegativeButton(negativeButtonText, negativeListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    public static void showYesNoNeutralDialog(Context context, String title, String positiveButtonText, String negativeButtonText, String neutralButtonText, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener neutralListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setTitle(title)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveListener)
                // this swap between negative and neutral button is intentional
                .setNegativeButton(neutralButtonText, neutralListener)
                .setNeutralButton(negativeButtonText, negativeListener)
                .show();

        setAlertDialogButtonColor(alertDialog, ResourceUtil.getAccentColor(context));
    }

    private static void setAlertDialogButtonColor(AlertDialog alertDialog, int color) {
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(color);
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(color);
    }

    public interface InputCallback {
        void onInput(DialogInterface dialog, CharSequence input);
    }
}