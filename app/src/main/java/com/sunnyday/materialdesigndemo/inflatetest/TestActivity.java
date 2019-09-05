package com.sunnyday.materialdesigndemo.inflatetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import com.sunnyday.materialdesigndemo.R;

/**
 *
 * LayoutInflater 的测试：https://blog.csdn.net/qq_38350635/article/details/100542733
 * */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        LinearLayoutCompat layoutRoot = findViewById(R.id.layout_root);  // TestActivity根布局的View对象

        LayoutInflater inflater = LayoutInflater.from(this);
        View mView = inflater.inflate(R.layout.layout_mytest, null, false);  // 布局转换为view
        layoutRoot.addView(mView);

    }
}
