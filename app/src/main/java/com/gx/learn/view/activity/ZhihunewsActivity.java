package com.gx.learn.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gx.learn.R;
import com.gx.learn.model.Zhihunews;
import com.gx.learn.net.GetData;

import rx.Subscriber;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ZhihunewsActivity extends AppCompatActivity {
    private String title = "title";
    private String images = "images";
    private String id = "id";
    int newsid;
    private WebView webView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihunews);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = getIntent().getStringExtra(title);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        images = getIntent().getStringExtra(images);
        newsid  = Integer.valueOf(getIntent().getStringExtra(id));

        imageView = (ImageView) findViewById(R.id.zhihunews_image);
        webView = (WebView) findViewById(R.id.zhuhunews_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //        加载缓存，如果不存在就加载网络数据
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //        app cache
        settings.setAppCacheEnabled(true);
        //        dom storage
        settings.setDomStorageEnabled(true);
        //        database cache
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);

        getZhihunews();
    }
    private void getZhihunews() {
        Subscriber<Zhihunews> subscriber = new Subscriber<Zhihunews>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Zhihunews zhihunews) {
                System.out.println(zhihunews.getTitle());
                Glide.with(ZhihunewsActivity.this)
                        .load(zhihunews.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
                String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news\" " +
                        "type=\"text/css\">";
                String html = "<html><head>" + css + "</head><body>" + zhihunews.getBody() + "</body></html>";
                html = html.replace("<div class=\"img-place-holder\">", "");
                webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
//                webView.setWebViewClient(new WebViewClient(){
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        view.loadUrl(url);
//                        return true;
//                    }
//                });
            }


        };

        GetData.getInstance().getzhihudnews(subscriber,newsid);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
