package com.see.you.plan.test.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.android.base.base.OverStatusBarActivity;
import com.see.you.plan.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 15:16
 * desc    :
 * modify  :
 * version : 1.0
 */

public class FragActivity extends OverStatusBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
    }
}
