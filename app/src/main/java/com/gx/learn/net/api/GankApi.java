package com.gx.learn.net.api;


import com.gx.learn.model.Meizi;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by gx on 16/11/2.
 */

public interface GankApi {
    String GANK_API = "http://gank.io/api/";
    @GET("data/福利/10/{page}")
    Observable<Meizi> getmeizi(@Path("page") int page);
}
