package com.noelchew.ncutils.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.noelchew.ncutils.demo.holder.DummyViewHolder;
import com.noelchew.ncutils.demo.model.DummyObject;

/**
 * Created by noelchew on 03/11/2016.
 */

public class DummyAdapter extends RecyclerArrayAdapter<DummyObject> {

    Context context;

    public DummyAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DummyViewHolder(parent);
    }
}
