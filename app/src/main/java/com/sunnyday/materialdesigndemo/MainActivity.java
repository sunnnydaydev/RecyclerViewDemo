package com.sunnyday.materialdesigndemo;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;


import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.sunnyday.materialdesigndemo.adapter.BaseAdapter;
import com.sunnyday.materialdesigndemo.adapter.MyRecyclerAdapter;
import com.sunnyday.materialdesigndemo.adapter.MyRecyclerAdapterWrapper;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private MyRecyclerAdapter mAdapter;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // recycler view 的数据
        mList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mList.add("item" + i);
        }

        mAdapter = new MyRecyclerAdapter(mList, this);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(manager);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
       // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setItemAnimator(new ScaleInAnimator());//使用第三方
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new MyRecyclerAdapter.OnClickListener() {
            @Override
            public void clicked(View view, int position) {
                Toast.makeText(MainActivity.this, "点击"+position, Toast.LENGTH_SHORT).show();
                mList.remove(position);
                mAdapter.notifyItemChanged(position);
            }
        });

//        mRecyclerView.setAdapter(new BaseAdapter(this,R.layout.layout_recyclerview_item,mList) {
//            @Override
//            public void convert(BaseAdapter.BaseViewHolder baseViewHolder, int position) {
//               AppCompatTextView text = baseViewHolder.getView(R.id.atv_text);
//               text.setText("万能适配器："+position);
//            }
//        });


        // 滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlide2Up = false;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//状态为静止没有滑动时
                    if (manager != null) {
                        int lastItemIndex = manager.findLastCompletelyVisibleItemPosition();//获取最后一个可见的条目索引
                        int itemCount = manager.getItemCount();//获取item的数量

                        // 当滑动到最后一个可见条目，且上拉时。加载数据
                        if (lastItemIndex == itemCount - 1 && isSlide2Up) {
                            mList.add("我是上拉加载的数据");
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "刷新数据成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
                isSlide2Up = dy > 0;
            }
        });


        refreshLayout.setOnRefreshListener(this);// 下拉刷新
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);//刷新时进度条颜色变换，转一圈颜色变化一种
    }

    /**
     * 初始化view
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.recycle_view);
        refreshLayout = findViewById(R.id.pull2refresh);
    }

    /**
     * 下拉刷新回调，模拟数据更新
     */
    @Override
    public void onRefresh() {
        //模拟耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);//取消刷新
            }
        }, 2000);

        mList.add("我是下拉刷新添加的数据");
        mAdapter.notifyDataSetChanged();
        Toast.makeText(this, "刷新数据成功", Toast.LENGTH_SHORT).show();
    }
}
