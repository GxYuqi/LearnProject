package com.gx.learn.net;

import android.util.Log;

import com.gx.learn.model.Android;
import com.gx.learn.model.Blog;
import com.gx.learn.model.Gril;
import com.gx.learn.model.Meizi;
import com.gx.learn.model.Zhihu;
import com.gx.learn.model.Zhihunews;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by gx on 16/11/2.
 */

public class GetData {
    private static final String TAG = "GetData";

    private GetData(){

    }

    public static GetData getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder{
        private static final GetData INSTANCE = new GetData();
    }

    public void getmeizidata(Subscriber<List<Gril>> subscriber ,final int page){
        Log.e(TAG,page + "");
        Network.getGankApi()
                .getmeizi(page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Meizi, List<Gril>>() {
                    @Override
                    public List<Gril> call(Meizi meizi) {
                        List<Meizi.Results> results = meizi.getResults();
                        List<Gril> grils = new ArrayList<>();
                        for (Meizi.Results result : results) {
                            Gril gril = new Gril();
                            gril.descr = result.getDesc();
                            gril.Url = result.getUrl();
                            grils.add(gril);
                        }
                        return grils;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void getblogdata(Subscriber<List<Android>> subscriber2 , final int page){
        Log.e(TAG,page + "");
        Network.getBlogApi()
                .getblog(page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Blog, List<Android>>() {
                    @Override
                    public List<Android> call(Blog blog) {
                        List<Blog.Result> results = blog.getResult();
                        List<Android> androids = new ArrayList<>();
                        for (Blog.Result result : results) {
                            Android android = new Android();
                            android.desc = result.getDesc();
                            android.url = result.getUrl();
                            android.who = result.getWho();
                            androids.add(android);
                        }
                        return androids;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber2);

    }

    public void getzhihudatafirst(Subscriber<Zhihu> subscriber , final String latest){
//        Log.e(TAG,page + "");
        Network.getZhihuApi()
                .getzhihufirst(latest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    public void getzhihudata(Subscriber<Zhihu> subscriber , final String data){
//        Log.e(TAG,page + "");
        Network.getZhihuApi()
                .getzhihu(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void getzhihudnews(Subscriber<Zhihunews> subscriber , final int data){
//        Log.e(TAG,page + "");
        Network.getZhihuApi()
                .getzhihunews(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
