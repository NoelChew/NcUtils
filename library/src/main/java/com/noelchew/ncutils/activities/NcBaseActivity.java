package com.noelchew.ncutils.activities;

/**
 * Created by noelchew on 7/4/16.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.noelchew.ncutils.KeyboardUtil;
import com.noelchew.ncutils.R;

public abstract class NcBaseActivity extends AppCompatActivity {

    protected Context context;
    private Toolbar toolbar;
    protected ProgressDialog mProgressDialog;
    protected Menu menu;
    protected String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutResource());
        setupToolbar();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(R.string.ncutils_loading);
        mProgressDialog.setMessage(getString(R.string.ncutils_please_wait));
    }

    @Override
    protected void onStop() {
        KeyboardUtil.collapseKeyboard(this);
        super.onStop();
    }

    protected void setToolbarTitle(String title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());
        }
    }

    protected abstract int getLayoutResource();

    protected abstract int getToolbarResourceId();

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(getToolbarResourceId());
        if (toolbar != null) {
            toolbar.setTitle(getToolbarTitle());
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());
        }
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    protected abstract boolean getDisplayHomeAsUpEnabled();

    protected abstract String getToolbarTitle();

    protected void hideMenuItem(int id) {
        MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setVisible(false);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void hideSubMenuItem(int menuId, int subMenuId) {
        MenuItem item = menu.findItem(menuId).getSubMenu().findItem(subMenuId);
        if (item != null) {
            item.setVisible(false);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void showMenuItem(int id) {
        MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setVisible(true);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void showSubMenuItem(int menuId, int subMenuId) {
        MenuItem item = menu.findItem(menuId).getSubMenu().findItem(subMenuId);
        if (item != null) {
            item.setVisible(true);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setMenuItemTitle(int id, String title) {
        MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setTitle(title);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setMenuItemDrawable(int id, Drawable drawable) {
        MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setIcon(drawable);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setMenuItemEnabled(int id, boolean enabled) {
        MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setEnabled(enabled);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setMenuItemVisibility(int id, boolean visible) {
        MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setVisible(visible);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setSubMenuItemTitle(int menuId, int subMenuId, String title) {
        MenuItem item = menu.findItem(menuId).getSubMenu().findItem(subMenuId);
        if (item != null) {
            item.setTitle(title);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setSubMenuItemDrawable(int menuId, int subMenuId, Drawable drawable) {
        MenuItem item = menu.findItem(menuId).getSubMenu().findItem(subMenuId);
        if (item != null) {
            item.setIcon(drawable);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setSubMenuItemEnabled(int menuId, int subMenuId, boolean enabled) {
        MenuItem item = menu.findItem(menuId).getSubMenu().findItem(subMenuId);
        if (item != null) {
            item.setEnabled(enabled);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    protected void setSubMenuItemVisibility(int menuId, int subMenuId, boolean visible) {
        MenuItem item = menu.findItem(menuId).getSubMenu().findItem(subMenuId);
        if (item != null) {
            item.setVisible(visible);
        } else {
            Log.e(TAG, "Cannot find menu.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            KeyboardUtil.collapseKeyboard(this);
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null && !mProgressDialog.isShowing()) {
                    mProgressDialog.show();
                }
            }
        });

    }

    public void showProgressDialog(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null && !mProgressDialog.isShowing()) {
                    mProgressDialog.setMessage(msg);
                    mProgressDialog.show();
                }
            }
        });

    }

    public void hideProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });

    }
}
