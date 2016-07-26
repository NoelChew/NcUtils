package com.noelchew.ncutils.activities;

import android.os.Bundle;
import android.util.Log;

import com.noelchew.ncutils.AppSettingsUtil;
import com.noelchew.ncutils.NcAppRatingUtil;
import com.noelchew.ncutils.R;

/**
 * Created by noelchew on 7/4/16.
 */

// this parent activity should only be used for first activity (after SplashScreen) like MainActivity.
// other activities should extend NcBaseActivity
public abstract class MainNcBaseActivity extends NcBaseActivity {

    protected String TAG = "MainNcBaseActivity";
    protected NcAppRatingUtil ncAppRatingUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ncAppRatingUtil = new NcAppRatingUtil(mContext, getConfig(), ncAppRatingUtilCallback);
        ncAppRatingUtil.showRateDialogIfNeeded();
    }

    protected NcAppRatingUtil.Config getConfig() {
        NcAppRatingUtil.Config config = new NcAppRatingUtil.Config();
        return config;
    }

    protected NcAppRatingUtil.Callback ncAppRatingUtilCallback = new NcAppRatingUtil.Callback() {
        @Override
        public void onOpenMarket(int rating) {
            Log.d(TAG, "NcAppRatingUtil - onOpenMarket() rating: " + String.valueOf(rating));
            AppSettingsUtil.rateUs(mContext);
        }

        @Override
        public void onNoClicked() {
            Log.d(TAG, "NcAppRatingUtil - onNoClicked()");
        }

        @Override
        public void onCancelClicked() {
            Log.d(TAG, "NcAppRatingUtil - onCancelClicked()");
        }

        @Override
        public void onShowFeedbackDialog(int rating) {
            Log.d(TAG, "NcAppRatingUtil - onShowFeedbackDialog() rating: " + String.valueOf(rating));
//            AppSettingsUtil.feedbackWithBadRating(mContext, "YourSparkPostApiKey", mContext.getString(R.string.app_name), "chewwengchuen@gmail.com", mProgressDialog);
            AppSettingsUtil.feedbackWithBadRating(mContext, "2f0cc0610da8254a5c8c73185ded32c3cc36089e", mContext.getString(R.string.app_name), "chewwengchuen@gmail.com", mProgressDialog);
        }
    };
}
