package com.noelchew.ncutils.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialCommunityIcons;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.kbeanie.multipicker.api.entity.ChosenVideo;
import com.noelchew.ncutils.AlertDialogUtil;
import com.noelchew.ncutils.NcAppRatingUtil;
import com.noelchew.ncutils.PickerUtil;
import com.noelchew.ncutils.activities.MainNcBaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noelchew on 7/4/16.
 */
public class DemoActivity extends MainNcBaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_demo;
    }

    @Override
    protected int getToolbarResourceId() {
        return R.id.toolbar;
    }

    @Override
    protected boolean getDisplayHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected String getToolbarTitle() {
        return "NcUtils Demo";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_demo, menu);
        menu.findItem(R.id.menu_attachment).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_attachment)
                        .colorRes(android.R.color.white)
                        .actionBarSize());
        menu.findItem(R.id.menu_attachment).getSubMenu().findItem(R.id.menu_attachment_image).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_camera)
                        .colorRes(R.color.icon_color)
                        .actionBarSize());
        menu.findItem(R.id.menu_attachment).getSubMenu().findItem(R.id.menu_attachment_video).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_video)
                        .colorRes(R.color.icon_color)
                        .actionBarSize());
        menu.findItem(R.id.menu_rate).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_thumb_up)
                        .colorRes(android.R.color.white)
                        .actionBarSize());
        menu.findItem(R.id.menu_dummy2).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_dots_vertical)
                        .colorRes(android.R.color.white)
                        .actionBarSize());

        // demo: hiding of menu item
        hideMenuItem(R.id.menu_dummy2);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case android.R.id.home:
                // this is only used if getDisplayHomeAsUpEnabled() is set to true
                finish();
                break;

            case R.id.menu_attachment_image:
                onClickMenuImageAttachment();
                break;

            case R.id.menu_attachment_video:
                onClickMenuVideoAttachment();
                break;

            case R.id.menu_rate:
                ncAppRatingUtil.setOptOut(mContext, false);
                ncAppRatingUtil.showRateDialogIfNeeded();
                break;

            case R.id.menu_dummy2:
                Toast.makeText(mContext, "Dummy 1", Toast.LENGTH_SHORT).show();
                break;

            default:

                break;
        }

        return true;
    }

    // remove this part for production - this is only for testing
    protected NcAppRatingUtil.Config getConfig() {
        return new NcAppRatingUtil.Config(0, 0, true, 4, ncAppRatingUtilCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pickerUtil.setPickerUtilListener(pickerUtilListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (pickerUtil.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onClickMenuImageAttachment() {
        ArrayList<String> selectionArrayList = new ArrayList<>();
        selectionArrayList.add("Pick Image from Gallery");
        selectionArrayList.add("Capture with Camera");
        AlertDialogUtil.showAlertDialogWithSelections(mContext, selectionArrayList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        pickImage();
                        break;

                    case 1:
                        captureImage();
                        break;
                }
            }
        });
    }

    private void onClickMenuVideoAttachment() {
        ArrayList<String> selectionArrayList = new ArrayList<>();
        selectionArrayList.add("Pick Video from Gallery");
        selectionArrayList.add("Record with Camera");
        AlertDialogUtil.showAlertDialogWithSelections(mContext, selectionArrayList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        pickVideo();
                        break;

                    case 1:
                        recordVideo();
                        break;
                }
            }
        });
    }

    private PickerUtil.PickerUtilListener pickerUtilListener = new PickerUtil.PickerUtilListener() {
        @Override
        public void onPermissionDenied() {
            AlertDialogUtil.showAlertDialogMessage(mContext, "Message from activity level: Permission denied.", null);
        }

        @Override
        public void onImagesChosen(List<ChosenImage> list) {
            final Uri uri = Uri.fromFile(new File(list.get(0).getOriginalPath()));
            AlertDialogUtil.showYesNoDialog(mContext, "Image Selected:",
                    list.get(0).getOriginalPath(),
                    "Show",
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setDataAndType(uri, "image/*");
                            startActivity(intent);
                        }
                    },
                    null);
        }

        @Override
        public void onVideosChosen(List<ChosenVideo> list) {
            final Uri uri = Uri.fromFile(new File(list.get(0).getOriginalPath()));
            AlertDialogUtil.showYesNoDialog(mContext, "Video Selected:",
                    list.get(0).getOriginalPath(),
                    "Show",
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setDataAndType(uri, "video/*");
                            startActivity(intent);
                        }
                    },
                    null);
        }

        @Override
        public void onError(String s) {
            AlertDialogUtil.showAlertDialogMessage(mContext, s, null);
        }
    };


    private void pickImage() {
        pickerUtil.getPermissionAndPickSingleImage();
    }

    private void captureImage() {
        pickerUtil.getPermissionAndTakePicture();
    }

    private void pickVideo() {
        pickerUtil.getPermissionAndPickSingleVideo();
    }

    private void recordVideo() {
        pickerUtil.getPermissionAndTakeVideo();
    }

}
