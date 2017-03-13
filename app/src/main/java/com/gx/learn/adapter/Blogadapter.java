package com.gx.learn.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.gx.learn.model.Android;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by gx on 16/11/3.
 */

public class Blogadapter extends RecyclerArrayAdapter<Android> {
    public Blogadapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BlogViewHolder(parent);
    }
}
