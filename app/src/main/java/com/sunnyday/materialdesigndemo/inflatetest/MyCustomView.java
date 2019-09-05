package com.sunnyday.materialdesigndemo.inflatetest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunnyDay on 2019/9/4 20:34
 */
public class MyCustomView extends View {
    public MyCustomView(Context context) {
        this(context,null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 用户无论调用哪个方法，最终都会使用这个方法处理。
     * */
    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
