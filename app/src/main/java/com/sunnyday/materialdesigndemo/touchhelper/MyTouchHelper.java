package com.sunnyday.materialdesigndemo.touchhelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.sunnyday.materialdesigndemo.adapter.MyRecyclerAdapter;

import java.util.Collections;

/**
 * Created by sunnyDay on 2019/9/20 17:21
 * <p>
 * 侧滑 拖拽的实现
 */
public class MyTouchHelper extends ItemTouchHelper.Callback {

    private MyRecyclerAdapter mRecyclerAdapter;

    public MyTouchHelper(MyRecyclerAdapter recyclerAdapter) {
        mRecyclerAdapter = recyclerAdapter;
    }

    /**
     * 设置支持拖拽滑动的方向（内部通过makeMovementFlags方法设置）
     * 规定条目滑动为：左右
     * 规定条目拖拽为：上下
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int slideFlag = ItemTouchHelper.START | ItemTouchHelper.END; // 左右滑动
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //上下拖拽
        return makeMovementFlags(dragFlag, slideFlag);
    }

    /**
     * 拖拽时回调
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        int fromItem = viewHolder.getAdapterPosition();
        int toItem = viewHolder1.getAdapterPosition();
        Collections.swap(mRecyclerAdapter.getmList(), fromItem, toItem);
        mRecyclerAdapter.notifyItemRangeRemoved(fromItem, toItem);
        return true;
    }

    /**
     * 滑动时回调
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        mRecyclerAdapter.getmList().remove(position);
        mRecyclerAdapter.notifyItemRemoved(position);
    }

    /**
     * 状态改变时回调
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#ff0000")); //设置拖拽和侧滑时的背景色
        }
    }

    /**
     * 拖拽滑动完成之后回调
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    /**
     * 如果想自定义动画，可以重写这个方法
     * 根据偏移量来设置
     */
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 是否支持长摁拖拽，默认为 true，设置false为关闭。
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}
