package com.sunnyday.materialdesigndemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sunnyday.materialdesigndemo.R;

/**
 * Created by sunnyDay on 2019/9/6 20:02
 * recyclerView的多条目实现
 */
public class MyRecyclerAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {// 1、装饰者与被装饰着继承或实现相同的对象

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
     * @param viewType 代表条目类型
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(LayoutInflater.from(myRecyclerAdapter.getContext()).inflate(R.layout.layout_head, viewGroup, false));
        } else if (viewType == TYPE_FOOT) {
            return new FootViewHolder(LayoutInflater.from(myRecyclerAdapter.getContext()).inflate(R.layout.layout_foot, viewGroup, false));
        } else {
            return myRecyclerAdapter.onCreateViewHolder(viewGroup, viewType);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (position == 0 || position == myRecyclerAdapter.getItemCount() + 1) {
            // 索引为 0和最后一个条目时 加载相应的布局
        } else {
            if (viewHolder instanceof MyRecyclerAdapter.MyViewHolder) {
                myRecyclerAdapter.onBindViewHolder((MyRecyclerAdapter.MyViewHolder) viewHolder, position - 1);
            }

        }
    }


    @Override
    public int getItemCount() {
        return myRecyclerAdapter.getItemCount() + 2; // 相当于原来的基础上多添加两个
    }

    /**
     * @param position 条目索引
     * @function 根据条目的索引返回其相应的条目类型。相同类型item返回相同的数值。
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position == myRecyclerAdapter.getItemCount() + 1) {
            return TYPE_FOOT;
        } else
            return TYPE_NORMAL;

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


    class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
