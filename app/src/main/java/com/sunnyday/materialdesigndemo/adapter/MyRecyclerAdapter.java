package com.sunnyday.materialdesigndemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(View.inflate(mContext, R.layout.layout_recyclerview_item, null));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.text.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size(); // 三目运算，避免空指针。
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.atv_text);
        }
    }
}
