package com.gx.learn.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.learn.R;
import com.gx.learn.model.Android;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by gx on 16/11/3.
 */

public class BlogViewHolder extends BaseViewHolder<Android> {
    private TextView textView;
    private TextView textView2;
    public BlogViewHolder(ViewGroup parent) {
        super(parent, R.layout.androidblog_item);
        textView = $(R.id.androidblog_title);
        textView2 = $(R.id.androidblog_who);
    }
    @Override
    public void setData(Android data) {
        super.setData(data);
        //Log.e("GirlViewHolder",data.imageUrl);
        textView.setText(data.desc);
        textView2.setText(data.who);
    }
}
