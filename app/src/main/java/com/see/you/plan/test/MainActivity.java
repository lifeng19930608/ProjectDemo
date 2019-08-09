package com.see.you.plan.test;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.content.FileProvider;

import com.android.base.mob.listener.MobActionListener;
import com.android.base.mob.login.LoginView;
import com.android.base.mob.share.ShareBottomDialog;
import com.android.base.moudle.NewVersionBean;
import com.android.base.mvp.BaseModel;
import com.android.base.mvp.MvpActivity;
import com.android.base.utils.ContextUtils;
import com.android.base.utils.FastClickUtils;
import com.android.base.utils.IntentUtils;
import com.android.base.utils.LogUtils;
import com.android.base.utils.ToastUtils;
import com.see.you.plan.R;
import com.see.you.plan.app.RealApplication;
import com.see.you.plan.test.component.ComponentActivity;
import com.see.you.plan.test.fragment.FragActivity;
import com.see.you.plan.test.load.LoadActivity;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, View.OnClickListener {

    private Button share;
    private Button login;
    private Button network;
    private Button fragment;
    private Button component;
    private Button load;
    private LoginView login_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setListener();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        share = findViewById(R.id.share);
        login = findViewById(R.id.login);
        network = findViewById(R.id.network);
        fragment = findViewById(R.id.fragment);
        component = findViewById(R.id.component);
        load = findViewById(R.id.load);
        login_view = findViewById(R.id.login_view);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setListener() {
        share.setOnClickListener(this);
        login.setOnClickListener(this);
        network.setOnClickListener(this);
        fragment.setOnClickListener(this);
        component.setOnClickListener(this);
        load.setOnClickListener(this);
        login_view.setMobActionListener(new MobActionListener() {
            @Override
            public void onComplete() {
                LogUtils.i("=====登陆", "onComplete");
            }

            @Override
            public void onError() {
                LogUtils.i("=====登陆", "onError");
            }

            @Override
            public void onCancel() {
                LogUtils.i("=====登陆", "onCancel");
            }
        });
    }

    @Override
    public void getDataSuccess(BaseModel model) {
        LogUtils.i("=========", "2222222222" + model.status);
        NewVersionBean bean = (NewVersionBean) model.data;
        ToastUtils.longShow(model.message);
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtils.longShow(msg);
    }


    @Override
    public void onClick(View view) {
        if (FastClickUtils.isFastDoubleClick(String.valueOf(view.getId()))) {
            return;
        }
        switch (view.getId()) {
            case R.id.share:
                ShareBottomDialog shareBottomDialog = new ShareBottomDialog(MainActivity.this);
                shareBottomDialog.setTitle("分享测试");
                shareBottomDialog.setText("分享测试内容");
                shareBottomDialog.setUrl("http://www.baidu.com");
                shareBottomDialog.show();
                shareBottomDialog.setMobActionListener(new MobActionListener() {
                    @Override
                    public void onComplete() {
                        LogUtils.i("=====分享", "onComplete");
                    }

                    @Override
                    public void onError() {
                        LogUtils.i("=====分享", "onError");
                    }

                    @Override
                    public void onCancel() {
                        LogUtils.i("=====分享", "onCancel");
                    }
                });
                break;
            case R.id.login:
                if (login_view.getVisibility() == View.VISIBLE) {
                    login_view.setVisibility(View.GONE);
                } else {
                    login_view.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.network:
                presenter.version("1.0", 1);
                presenter.version("2.0", 2);
                presenter.version("3.0", 3);
                break;
            case R.id.fragment:
                IntentUtils.startActivity(FragActivity.class);
                break;
            case R.id.component:
                IntentUtils.startActivity(ComponentActivity.class);
                break;
            case R.id.load:
                IntentUtils.startActivity(LoadActivity.class);
                break;
        }
    }

}
