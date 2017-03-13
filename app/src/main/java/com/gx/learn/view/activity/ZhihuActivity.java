package com.gx.learn.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gx.learn.R;
import com.gx.learn.adapter.Zhihuadapter;
import com.gx.learn.model.Zhihu;
import com.gx.learn.model.Zhiitem;
import com.gx.learn.net.GetData;
import com.gx.learn.view.CircularAnim;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class ZhihuActivity extends AppCompatActivity {
    private String latest = "latest" ;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EasyRecyclerView recyclerView;
    private Zhihuadapter zhihuadapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Zhihu.Top_stories> topStories;
    private List<Zhiitem> zhiitems ;
    private RollPagerView rollViewPager;
     private String data = "";
    private TestLoopAdapter testLoopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("知乎日报");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (EasyRecyclerView) findViewById(R.id.recycleview);
        linearLayoutManager = new LinearLayoutManager(ZhihuActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        zhihuadapter = new Zhihuadapter(ZhihuActivity.this);
        recyclerView.setAdapter(zhihuadapter);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiprefresh_zhihu) ;
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        init();
    }

    private void init() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getZhihudDatafirst();
            }
        },10);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                        zhihuadapter.clear();

                        getZhihudDatafirst();
                        System.out.println(zhiitems.get(0).title);

                    }
                },300);
            }
        });
        zhihuadapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getZhihuData();
            }

            @Override
            public void onMoreClick() {

            }
        });
        zhihuadapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ZhihuActivity.this,ZhihunewsActivity.class);
                intent.putExtra("title",zhihuadapter.getItem(position).title);
                intent.putExtra("images",zhihuadapter.getItem(position).images);
                intent.putExtra("id",String.valueOf(zhihuadapter.getItem(position).id));
                startActivity(intent);
            }
        });
    }
    private class TestLoopAdapter extends LoopPagerAdapter
    {
        private List<Zhihu.Top_stories> data;
        public TestLoopAdapter(RollPagerView viewPager, List<Zhihu.Top_stories> topStories)
        {
            super(viewPager);
            this.data = topStories;
        }

        @Override
        public View getView(ViewGroup container, final int position)
        {
            final ImageView view = new ImageView(container.getContext());
            Glide.with(container.getContext()).load(data.get(position).getImage()).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v)
                {
                    final Intent intent = new Intent(ZhihuActivity.this,ZhihunewsActivity.class);

                    intent.putExtra("id",String.valueOf(topStories.get(position).getId()));
                    intent.putExtra("title",topStories.get(position).getTitle());
                    CircularAnim.fullActivity(ZhihuActivity.this, view)
                            .colorOrImageRes(R.color.colorPrimary)
                            .duration(150)
                            .go(new CircularAnim.OnAnimationEndListener() {
                                @Override
                                public void onAnimationEnd() {
                                    startActivity(intent);
                                }
                            });


                }

            });

            return view;
        }

        @Override
        public int getRealCount()
        {
            return 5;
        }

    }


    private void getZhihudDatafirst() {
        Subscriber<Zhihu>  subscriber = new Subscriber<Zhihu>() {
            @Override
            public void onCompleted() {
                if (testLoopAdapter  == null){
                    zhihuadapter.addHeader(new RecyclerArrayAdapter.ItemView() {
                        @Override
                        public View onCreateView(ViewGroup parent) {
                            rollViewPager = (RollPagerView) LayoutInflater.from(ZhihuActivity.this).inflate(R.layout.zhihuheader, recyclerView, false);
                            testLoopAdapter = new TestLoopAdapter(rollViewPager,topStories);
                            rollViewPager.setAdapter(testLoopAdapter);
                            rollViewPager.setHintView(new ColorPointHintView(ZhihuActivity.this, Color.WHITE, Color.GRAY));// 设置圆点指示器颜色
                            return rollViewPager;
                        }

                        @Override
                        public void onBindView(View headerView) {

                        }
                    });
                }
                zhihuadapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("555555");
            }

            @Override
            public void onNext(Zhihu zhihu) {
                data = zhihu.getDate();
                List<Zhihu.Stories> stories = zhihu.getStories();
                topStories = zhihu.getTop_stories();
                zhiitems = new ArrayList<>();
                for (int i = 0; i <stories.size();i++ ){
                    Zhiitem zhiitem = new Zhiitem();
                    zhiitem.id = stories.get(i).getId();
                    zhiitem.title = stories.get(i).getTitle();
                    zhiitem.images = stories.get(i).getString().get(0);
                    zhiitems.add(zhiitem);
                }
                zhihuadapter.addAll(zhiitems);


            }

        };

        GetData.getInstance().getzhihudatafirst(subscriber,latest);

    }

    private void getZhihuData(){
        Subscriber<Zhihu>  subscriber = new Subscriber<Zhihu>() {
            @Override
            public void onCompleted() {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("666666");
            }

            @Override
            public void onNext(Zhihu zhihu) {
                data = zhihu.getDate();
                List<Zhihu.Stories> stories = zhihu.getStories();
                 zhiitems = new ArrayList<>();
                for (int i = 0; i <stories.size();i++ ){
                    Zhiitem zhiitem = new Zhiitem();
                    zhiitem.id = stories.get(i).getId();
                    zhiitem.title = stories.get(i).getTitle();
                    zhiitem.images = stories.get(i).getString().get(0);
                    zhiitems.add(zhiitem);
                }
                zhihuadapter.addAll(zhiitems);
            }

        };
        GetData.getInstance().getzhihudata(subscriber,data);

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
