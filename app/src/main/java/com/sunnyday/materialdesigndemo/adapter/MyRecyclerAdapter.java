package com.sunnyday.materialdesigndemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
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
    private List<String> mList;
    private Context mContext;

    public MyRecyclerAdapter(List<String> list, Context context) {
        mContext = context;
        mList = list;
    }

    /**
     * ViewHolder 创建时，这个方法回调。
     *
     * */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //java.lang.IllegalStateException: ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)
        //  View mView = View.inflate(mContext, R.layout.layout_recyclerview_item, null);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.layout_recyclerview_item, viewGroup, false);//最后一个参数必须为false
        return new MyViewHolder(mView);// 吧view传递给ViewHolder
    }

    /**
     *
     * 当绑定ViewHolder 时这个方法回调。
     * */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.text.setText(mList.get(i));
    }

    /**
     * RecyclerView  item数目
     * */
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size(); // 三目运算，非空处理，避免空指针。
    }


    /**
     * 提供ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.atv_text);
        }
    }
}
