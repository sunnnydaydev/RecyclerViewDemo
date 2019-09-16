package com.sunnyday.materialdesigndemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sunnyDay on 2019/9/16 17:48
 */
public abstract class MultiItemBaseAdapter<T> extends BaseAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemBaseAdapter(Context context, List<T> data, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, data);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mData.get(position));
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        return BaseViewHolder.getViewHolder(mContext, viewGroup, layoutId);
    }


/**
 *
 * 我们的ViewHolder是通用的，唯一依赖的就是个layoutId。那么上述第二条就变成，
 * 根据不同的itemView告诉我用哪个layoutId即可，生成viewholder这种事我们通用adapter来做。
 * */
    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int itemType);
        int getItemViewType(int position, T t);
    }
}
