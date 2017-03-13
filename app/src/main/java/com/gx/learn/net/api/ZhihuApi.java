package com.gx.learn.net.api;


import com.gx.learn.model.Zhihu;
import com.gx.learn.model.Zhihunews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by gx on 16/11/4.
 */

public interface ZhihuApi {
    String ZHIHU_API = "http://news-at.zhihu.com/api/";
    @GET("4/news/{latest}")
    Observable<Zhihu>getzhihufirst(@Path("latest") String latest);

    @GET("4/news/before/{data}")
    Observable<Zhihu>getzhihu(@Path("data") String data);

    @GET("4/news/{data}")
    Observable<Zhihunews>getzhihunews(@Path("data") int data);

}
