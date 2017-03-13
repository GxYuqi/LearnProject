package com.gx.learn.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.learn.R;
import com.gx.learn.adapter.Blogadapter;
import com.gx.learn.model.Android;
import com.gx.learn.net.GetData;
import com.gx.learn.view.activity.WebActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {
    private static final String TAG = "BlogFragment";
    private EasyRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Blogadapter blogadapter;
    private LinearLayoutManager linearLayoutManager;
    private int page = 1;

    public BlogFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        recyclerView = (EasyRecyclerView) view.findViewById(R.id.recycleview);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        blogadapter = new Blogadapter(getContext());
        recyclerView.setAdapter(blogadapter);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiprefresh2) ;
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        init();
        return view;

    }

    private void init() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getBlogData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                        blogadapter.clear();
                        page = 1;
                        getBlogData();
                    }
                },1000);
            }
        });
        blogadapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getBlogData();
            }

            @Override
            public void onMoreClick() {

            }
        });
        blogadapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url",blogadapter.getItem(position).url);
                intent.putExtra("title",blogadapter.getItem(position).desc);
                startActivity(intent);
            }
        });
    }

    private void getBlogData() {
        Subscriber<List<Android>> subscriber2 = new Subscriber<List<Android>>() {
            @Override
            public void onCompleted() {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("88888");
            }

            @Override
            public void onNext(List<Android> androids) {
//                System.out.println(androids.get(0).url);
                blogadapter.addAll(androids);
            }
        };
        GetData.getInstance()
                .getblogdata(subscriber2,page);
        page++;
    }


}
