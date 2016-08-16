package com.noelchew.ncutils.demo;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialCommunityIcons;
import com.noelchew.ncutils.ToastUtil;
import com.noelchew.ncutils.activities.NcBaseActivity;

/**
 * Created by noelchew on 7/4/16.
 */
public class DemoActivity extends NcBaseActivity {

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
                ToastUtil.toastShortMessage(mContext, "Attach Image Pressed");
                break;

            case R.id.menu_attachment_video:
                ToastUtil.toastShortMessage(mContext, "Attach Video Pressed");
                break;

            case R.id.menu_rate:
                ToastUtil.toastShortMessage(mContext, "Rating Pressed");
                break;

            case R.id.menu_dummy2:
                Toast.makeText(mContext, "Dummy 1", Toast.LENGTH_SHORT).show();
                break;

            default:

                break;
        }

        return true;
    }

}
