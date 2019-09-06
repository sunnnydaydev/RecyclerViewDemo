package com.sunnyday.materialdesigndemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sunnyDay on 2019/9/6 20:02
 * recyclerView的多条目实现
 */
public class MyRecyclerAdapterWrapper extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {// 1、装饰者与被装饰着继承或实现相同的对象

    private static final int TYPE_NORMAL = 0; //普通类型
    private static final int TYPE_HEAD = 1;  // 类型 头
    private static final int TYPE_FOOT = 2; //  类型 尾

    private MyRecyclerAdapter myRecyclerAdapter;// 2、装饰者持有被装饰者引用

    private View mHeadView;
    private View mFootView;

    /**
     * 初始化引用
     */
    public MyRecyclerAdapterWrapper(MyRecyclerAdapter myRecyclerAdapter) {
        this.myRecyclerAdapter = myRecyclerAdapter;
    }


    /**
     * @param i 代表条目类型
     */
    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_HEAD) {
            return new MyRecyclerAdapter.MyViewHolder(mHeadView);
        } else if (i == TYPE_NORMAL) {
            return new MyRecyclerAdapter.MyViewHolder(mFootView);
        } else {
            return myRecyclerAdapter.onCreateViewHolder(viewGroup, i);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.MyViewHolder myViewHolder, int i) {
        if (i == 0 || i == myRecyclerAdapter.getItemCount() + 1) {
            return;
        } else {
            myRecyclerAdapter.onBindViewHolder((MyRecyclerAdapter.MyViewHolder) myViewHolder, i - 1);
        }
    }

    @Override
    public int getItemCount() {
        return myRecyclerAdapter.getItemCount() + 2; // 相当于原来的基础上多添加两个
    }

    /**
     * 重写getItemViewType 控制不同类型条目的展示
     * <p>
     * ps：不重写时默认返回0代表recycler view 只有一种条目（默认的0其实也就是一种类型表示）
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_NORMAL;
        } else if (position == 1) {
            return TYPE_HEAD;
        } else {
            return TYPE_FOOT;
        }

    }

    /**
     * @param headView 用户提供的头部view
     * @function 设置头
     */
    public void addHeadView(View headView) {
        mHeadView = headView;
    }


    /**
     * @param footView 用户提供的设置尾部的view
     * @function 设置尾
     */
    public void addFootView(View footView) {
        mFootView = footView;
    }


}
