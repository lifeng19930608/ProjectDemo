package com.see.you.plan.component;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.base.base.BaseActivity;
import com.android.base.utils.FastClickUtils;
import com.see.you.plan.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:50
 * desc    : 用于展示Component组件的使用
 * modify  :
 * version : 1.0
 */

public class ComponentActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout frame;
    private TextView page1;
    private TextView page2;
    private Component1 component1;
    private Component2 component2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        frame = findViewById(R.id.frame);
    }

    private void initData() {
        component1 = new Component1(this, frame);
        component1.onInit();
        component1.onShow();
        component2 = new Component2(this, frame);
        component2.onInit();
    }

    private void setListener() {
        page1.setOnClickListener(this);
        page2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (FastClickUtils.isFastDoubleClick(String.valueOf(view.getId()))) {
            return;
        }
        switch (view.getId()) {
            case R.id.page1:
                component1.onShow();
                component2.onHide();
                break;
            case R.id.page2:
                component2.onShow();
                component1.onHide();
                break;
        }
    }
}
