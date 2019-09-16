package com.sunnyday.materialdesigndemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sunnyDay on 2019/9/16 16:36
 * 通用的Adapter封装
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {


    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mData;
    protected LayoutInflater mLayoutInflate;


    public BaseAdapter(Context context, int layoutId, List<T> data) {
        mContext = context;
        mLayoutId = layoutId;
        mData = data;
        mLayoutInflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        return BaseViewHolder.getViewHolder(mContext, viewGroup, mLayoutId);

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        convert(baseViewHolder, position);
    }


    public abstract void convert(BaseViewHolder baseViewHolder, int position);


    /**
     * 返回条目个数（进行啦非空处理）
     */
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 通用的ViewHolder，内部使用SparseArray来缓存View对象
     */
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;//键值对为int类型，存储相对于hashMap高效
        private View mConvertView;
        private Context mContext;


        BaseViewHolder(Context context, @NonNull View itemView, ViewGroup parent) {
            super(itemView);
            mContext = context;
            mConvertView = itemView;
            mViews = new SparseArray<>();
        }

        /**
         * 获取ViewHolder
         */
        public static BaseViewHolder getViewHolder(Context context, ViewGroup parent, int layoutId) {
            View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
            return new BaseViewHolder(context, view, parent);
        }


        /**
         * @param viewId view的id
         * @function通过View Id 找到该控件
         */
        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }
}
