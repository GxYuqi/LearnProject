package com.gx.learn.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.gx.learn.model.Zhiitem;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by gx on 16/11/5.
 */

public class Zhihuadapter extends RecyclerArrayAdapter<Zhiitem> {
    public Zhihuadapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZhihuViewHolder(parent);
    }
}
