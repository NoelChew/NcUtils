package com.noelchew.ncutils.demo.holder;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.noelchew.ncutils.demo.R;
import com.noelchew.ncutils.demo.adapter.DummyAdapter;
import com.noelchew.ncutils.demo.model.DummyObject;

/**
 * Created by noelchew on 03/11/2016.
 */

public class DummyViewHolder extends BaseViewHolder<DummyObject> {
    RelativeLayout relativeLayout;
    TextView tvName, tvDescription;
    DummyAdapter.OnItemClickListener listener;

    public DummyViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.list_item_dummy);
        relativeLayout = $(R.id.relative_layout);
        tvName = $(R.id.text_view_name);
        tvDescription = $(R.id.text_view_description);
    }

    @Override
    public void setData(final DummyObject data) {
        tvName.setText(data.getName());
        tvDescription.setText(data.getDescription());
    }
}
