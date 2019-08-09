package com.see.you.plan.test.component;

import android.view.View;
import android.widget.LinearLayout;

import com.android.base.base.BaseActivity;
import com.android.base.base.BaseComponent;
import com.see.you.plan.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:59
 * desc    :
 * modify  :
 * version : 1.0
 */

public class Component1 extends BaseComponent {

    private LinearLayout layout1;

    public Component1(BaseActivity activity, View rootView) {
        super(activity, rootView);
    }

    @Override
    public void onInit() {
        super.onInit();
        layout1 = findViewById(R.id.layout1);
    }

    @Override
    public void onShow() {
        super.onShow();
        layout1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHide() {
        super.onHide();
        layout1.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        layout1.removeAllViews();
    }

}
