package com.gx.learn.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gx.learn.R;
import com.gx.learn.util.ImgSaveUtil;
import com.gx.learn.view.TouchImageView;

public class ImageActivity extends BaseActivity {
    private String url = "url";
    private String desc = "desc";
    private TouchImageView img;
    private Bitmap bitmap;
    private PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        desc = getIntent().getStringExtra(desc);
        url = getIntent().getStringExtra(url);
        getSupportActionBar().setTitle(desc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();


    }

    private void initView() {
//        img = (TouchImageView) findViewById(R.id.image_touch);
//        img.setMaxZoom(4f);
        photoView = (PhotoView) findViewById(R.id.photoview);
        photoView.enable();
        photoView.setMaxScale(5);

        Glide.with(this)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {// 先下载图片然后再做一些合成的功能，比如项目中出现的图文混排
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        photoView.setImageBitmap(resource);
                        bitmap = resource;
                    }
                });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_meizi, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (item.getItemId() == R.id.action_save) {
            ImgSaveUtil.saveImage(this,url,bitmap,img,"saveImg");
            Toast.makeText(this,"已保存至图库",Toast.LENGTH_SHORT ).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
