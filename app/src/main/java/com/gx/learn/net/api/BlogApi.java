package com.gx.learn.net.api;


import com.gx.learn.model.Blog;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by gx on 16/11/3.
 */

public interface BlogApi {
    String GANK_API = "http://gank.io/api/";
    @GET("data/Android/10/{page}")
    Observable<Blog> getblog(@Path("page") int page);

}
