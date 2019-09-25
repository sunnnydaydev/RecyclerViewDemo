package com.sunnyday.materialdesigndemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sunnyday.materialdesigndemo.R;

import java.util.List;

/**
 * Created by sunnyDay on 2019/9/4 14:58
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private OnClickListener mOnClickListener;


    private List<String> mList;
    private Context mContext;

    public MyRecyclerAdapter(List<String> list, Context context) {
        mContext = context;
        mList = list;
    }

    public List<String> getmList() {
        return mList;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * ViewHolder 创建时，这个方法回调。
     *
     * @param viewGroup 容器
     * @param viewType  条目类型
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //重要事说三遍：最后一个参数必须为false、最后一个参数必须为false、最后一个参数必须为false
        View mView = LayoutInflater.from(mContext).inflate(R.layout.layout_recyclerview_item, viewGroup, false);
        return new MyViewHolder(mView);// 吧view传递给ViewHolder
    }

    /**
     * 当绑定ViewHolder 时这个方法回调
     *
     * @param myViewHolder 自定义的ViewHolder
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.text.setText(mList.get(position));
        myViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener!=null){
                    mOnClickListener.clicked(v, position);
                }

            }
        });
    }

    /**
     * RecyclerView 的item数目
     */
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size(); // 三目运算，非空处理，避免空指针。
    }

    /**
     * RecyclerView的条目类型 默认为一种类型
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    /**
     * 提供ViewHolder
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView text;
        private final LinearLayoutCompat layout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.atv_text);
            layout = itemView.findViewById(R.id.ll_layout);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void clicked(View view, int position);
    }
}
