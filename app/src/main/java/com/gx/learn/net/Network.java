package com.gx.learn.net;

import com.gx.learn.net.api.BlogApi;
import com.gx.learn.net.api.GankApi;
import com.gx.learn.net.api.ZhihuApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gx on 16/11/2.
 */

public class Network {
    private static GankApi gankApi;
    private static BlogApi blogApi;
    private static ZhihuApi zhihuApi;
    private static OkHttpClient okHttpClient;

    public static OkHttpClient initOkHttp(){
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(initOkHttp())
                    .baseUrl(GankApi.GANK_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }


    public static BlogApi getBlogApi() {
        if (blogApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(initOkHttp())
                    .baseUrl(BlogApi.GANK_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            blogApi = retrofit.create(BlogApi.class);
        }
        return blogApi;
    }
    public static ZhihuApi getZhihuApi() {
        if (zhihuApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(initOkHttp())
                    .baseUrl(ZhihuApi.ZHIHU_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            zhihuApi = retrofit.create(ZhihuApi.class);
        }
        return zhihuApi;
    }
}
