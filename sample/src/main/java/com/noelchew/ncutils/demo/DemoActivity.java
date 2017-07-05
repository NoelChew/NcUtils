package com.noelchew.ncutils.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialCommunityIcons;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.noelchew.ncutils.activities.NcBaseActivity;
import com.noelchew.ncutils.alert.AlertDialogUtil;
import com.noelchew.ncutils.alert.ToastUtil;
import com.noelchew.ncutils.demo.adapter.DummyAdapter;
import com.noelchew.ncutils.demo.data.DummyData;
import com.noelchew.ncutils.demo.model.DummyObject;
import com.noelchew.ncutils.device.NetworkUtil;
import com.noelchew.ncutils.ui.PixelUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by noelchew on 7/4/16.
 */
public class DemoActivity extends NcBaseActivity {

    DummyAdapter adapter;
    EasyRecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    private static final int DATABASE_TAKE_ROW_COUNT = 8;

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
        menu.findItem(R.id.menu_add).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_plus)
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
                ToastUtil.toastShortMessage(context, "Attach Image Pressed");
                break;

            case R.id.menu_attachment_video:
                ToastUtil.toastShortMessage(context, "Attach Video Pressed");
                break;

            case R.id.menu_add:
                addData();
                break;

            case R.id.menu_dummy2:
                Toast.makeText(context, "Dummy 1", Toast.LENGTH_SHORT).show();
                break;

            default:

                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = (EasyRecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        // set onRefresh listener
        recyclerView.setRefreshListener(onRefreshListener);
        recyclerView.setRefreshingColorResources(R.color.colorAccent);

        adapter = new DummyAdapter(context);

        // setup load more listener
        adapter.setMore(R.layout.bottom_load_more, onMoreListener);

        adapter.setOnItemClickListener(onItemClickListener);
        adapter.setOnItemLongClickListener(onItemLongClickListener);

        recyclerView.setAdapter(adapter);

        // set view for empty list
        recyclerView.setAdapter(adapter);

        recyclerView.setRefreshing(true);

        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, PixelUtil.dpToPx(context, 0.5f), PixelUtil.dpToPx(context, 8), PixelUtil.dpToPx(context, 8));//color & height & paddingLeft & paddingRight
        itemDecoration.setDrawLastItem(true);//sometimes you don't want draw the divider for the last item,default is true.
        itemDecoration.setDrawHeaderFooter(false);//whether draw divider for header and footer,default is false.
        recyclerView.addItemDecoration(itemDecoration);

        onRefreshListener.onRefresh();

        NetworkUtil.openWifiSettings(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void addData() {
        Intent intent = new Intent(context, AddDataActivity.class);
        startActivity(intent);
    }

    private RecyclerArrayAdapter.OnItemClickListener onItemClickListener = new RecyclerArrayAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            ToastUtil.toastShortMessage(context, "onClicked: " + adapter.getItem(position).getName());
        }
    };

    private RecyclerArrayAdapter.OnItemLongClickListener onItemLongClickListener = new RecyclerArrayAdapter.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(final int position) {
            ArrayList<String> selections = new ArrayList<>(Arrays.asList(new String[]{"Edit", "Delete"}));
            AlertDialogUtil.showAlertDialogWithSelections(context, "Options", selections, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final DummyObject dummyObject = adapter.getItem(position);
                    switch (which) {
                        case 0:
                            AlertDialogUtil.showAlertDialogWithInput(context, "Edit", "Edit Item Name:", "Text", dummyObject.getName(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS, new AlertDialogUtil.InputCallback() {
                                @Override
                                public void onInput(DialogInterface dialog, CharSequence input) {
                                    dummyObject.setName(input.toString());
                                    dummyObject.update();
                                    ToastUtil.toastShortMessage(context, "Saved!");
                                    adapter.insert(dummyObject, position);
                                    adapter.remove(position);
                                }
                            }, "Save");

                            break;

                        case 1:
                            AlertDialogUtil.showYesNoDialog(context, "Delete Item", "Are you sure?", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dummyObject.delete();
                                    ToastUtil.toastShortMessage(context, "Item " + dummyObject.getName() + " is deleted from list.");
                                    adapter.remove(position);
                                }
                            });

                            break;
                    }
                }
            });

            return true;
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            adapter.clear();
            ArrayList<DummyObject> dummyObjectArrayList = new DummyObject().getPaginatedList(adapter.getCount(), DATABASE_TAKE_ROW_COUNT);
            if (dummyObjectArrayList == null || dummyObjectArrayList.isEmpty()) {
                AlertDialogUtil.showYesNoDialog(context, "No Data Found!", "Do you want to generate dummy data?", "Yes", "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<DummyObject> dummyObjectArrayList = DummyData.getGeneratedDummyData();
                        for (DummyObject dummyObject : dummyObjectArrayList) {
                            dummyObject.save();
                        }
                        adapter.addAll(dummyObjectArrayList);
                        recyclerView.setRefreshing(false);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recyclerView.setRefreshing(false);
                    }
                });
            } else {
                adapter.addAll(dummyObjectArrayList);
                recyclerView.setRefreshing(false);
            }
        }
    };

    private RecyclerArrayAdapter.OnMoreListener onMoreListener = new RecyclerArrayAdapter.OnMoreListener() {
        @Override
        public void onMoreShow() {
        }

        @Override
        public void onMoreClick() {
            ArrayList<DummyObject> dummyObjectArrayList = new DummyObject().getPaginatedList(adapter.getCount(), DATABASE_TAKE_ROW_COUNT);
            adapter.addAll(dummyObjectArrayList);
        }
    };
}
