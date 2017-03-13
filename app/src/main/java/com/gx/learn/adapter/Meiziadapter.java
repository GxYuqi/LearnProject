package com.gx.learn.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.gx.learn.model.Gril;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by gx on 16/11/2.
 */

public class Meiziadapter extends RecyclerArrayAdapter<Gril> {

    public Meiziadapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiziViewHolder(parent);
    }
}
