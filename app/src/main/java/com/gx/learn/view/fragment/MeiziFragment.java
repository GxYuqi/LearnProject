package com.gx.learn.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.learn.R;
import com.gx.learn.adapter.Meiziadapter;
import com.gx.learn.model.Gril;
import com.gx.learn.net.GetData;
import com.gx.learn.view.activity.ImageActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment {
    private static final String TAG = "MeiziFragment";
    private EasyRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Meiziadapter meiziadapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private int page = 1;

    public MeiziFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        recyclerView = (EasyRecyclerView) view.findViewById(R.id.recycleview);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        meiziadapter = new Meiziadapter(getContext());
        recyclerView.setAdapter(meiziadapter);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiprefresh) ;
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        init();
        return view;
    }

    private void init() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getGirlData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                        meiziadapter.clear();
                        page = 1;
                        getGirlData();
                    }
                },1000);
            }
        });
        meiziadapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getGirlData();
            }

            @Override
            public void onMoreClick() {

            }
        });
        meiziadapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra("url",meiziadapter.getItem(position).Url);
                intent.putExtra("desc",meiziadapter.getItem(position).descr);
                startActivity(intent);

            }
        });
    }


    private void getGirlData() {
        Subscriber<List<Gril>> subscriber = new Subscriber<List<Gril>>() {
            @Override
            public void onCompleted() {
             swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("2222");
            }

            @Override
            public void onNext(List<Gril> grils) {
                meiziadapter.addAll(grils);
                //System.out.println(grils.get(0).Url);
            }
        };
        GetData.getInstance()
                    .getmeizidata(subscriber,page);
        page++;

    }
}
