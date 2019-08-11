package com.see.you.plan.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.base.base.BaseEvent;
import com.android.base.config.Environment;
import com.android.base.config.HttpProtocol;
import com.android.base.mob.listener.MobActionListener;
import com.android.base.mob.login.LoginView;
import com.android.base.mob.share.ShareBottomDialog;
import com.android.base.moudle.LoveLedgerDataBean;
import com.android.base.moudle.NewVersionBean;
import com.android.base.mvp.BaseModel;
import com.android.base.mvp.MvpActivity;
import com.android.base.utils.FastClickUtils;
import com.android.base.utils.IntentUtils;
import com.android.base.utils.LogUtils;
import com.android.base.utils.PermissionUtils;
import com.android.base.utils.ToastUtils;
import com.see.you.plan.R;
import com.see.you.plan.test.component.ComponentActivity;
import com.see.you.plan.test.fragment.FragActivity;
import com.see.you.plan.test.load.LoadActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.storage.Storage;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, View.OnClickListener {

    private Button share;
    private Button login;
    private Button network;
    private Button fragment;
    private Button component;
    private Button load;
    private Button storage;
    private Button change;
    private TextView show;
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
        storage = findViewById(R.id.storage);
        change = findViewById(R.id.change);
        show = findViewById(R.id.show);
        login_view = findViewById(R.id.login_view);
    }

    @Override
    protected void initData() {
        Storage.get(this).writeInDatabase("key", "12345678");
        PermissionUtils.getInstance().request(getActivity(), PermissionUtils.Type.STORAGE, new PermissionUtils.Callback() {
            @Override
            public void onResult(boolean grant) {
                if (!grant) {
                    PermissionUtils.getInstance().showDialog(getActivity());
                }
            }
        });
        initEnvChange();
        EventBus.getDefault().post(new BaseEvent());
    }

    @Override
    protected void setListener() {
        share.setOnClickListener(this);
        login.setOnClickListener(this);
        network.setOnClickListener(this);
        fragment.setOnClickListener(this);
        component.setOnClickListener(this);
        load.setOnClickListener(this);
        storage.setOnClickListener(this);
        change.setOnClickListener(this);
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
    public <T> void getDataSuccess(BaseModel model, T t) {
        LogUtils.i("=========", "状态码：" + model.status + "信息：" + model.message);
//        NewVersionBean bean = (NewVersionBean) model.data;
//        LoveLedgerDataBean bean = (LoveLedgerDataBean) model.data;
//        LogUtils.i("=======返回数据", bean.pageNo + "" + bean.total);
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
//                presenter.version("2.0", 2);
//                presenter.version("3.0", 3);
//                presenter.code(1, "18702143494", 2);
//                HashMap<String, Object> params = new HashMap<>();
//                params.put("pageNo", 1);
//                params.put("pageSize", 20);
//                presenter.getList(params);
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
            case R.id.storage:
                LogUtils.i("========", Storage.get(this).readStringFromDatabase("key"));
                break;
            case R.id.change:
                show.setText(Environment.getEnv() + HttpProtocol.Domain.getBaseUrl());
                break;
        }
    }

    private void initEnvChange() {
        Spinner spSettingsEnv = findViewById(R.id.spSettingsEnv);
        if (Environment.getReleaseType().equals(Environment.RELEASE_TYPE_DEVELOP)) {
            // 内部开发版
            final List<String> envList = new ArrayList<>();
            envList.add("开发环境");
            envList.add("测试环境");
            envList.add("线上环境");
            SpinnerAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, envList);
            spSettingsEnv.setAdapter(adapter);
            int position = 1;
            switch (Environment.getEnv()) {
                case Environment.ENV_DEVELOP:
                    position = 0;
                    break;
                case Environment.ENV_TEST:
                    position = 1;
                    break;
                case Environment.ENV_PRODUCT:
                    position = 2;
                    break;
            }
            spSettingsEnv.setSelection(position);
            spSettingsEnv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                    int prePosition = 1;
                    switch (Environment.getEnv()) {
                        case Environment.ENV_DEVELOP:
                            prePosition = 0;
                            break;
                        case Environment.ENV_TEST:
                            prePosition = 1;
                            break;
                        case Environment.ENV_PRODUCT:
                            prePosition = 2;
                            break;
                    }

                    if (prePosition != position) {
                        if (isAlive()) {
                            switch (position) {
                                case 0:
                                    Environment.setEnv(Environment.ENV_DEVELOP);
                                    ToastUtils.shortShow("已切换到开发环境, 请重新打开APP");
                                    break;
                                case 1:
                                    Environment.setEnv(Environment.ENV_TEST);
                                    ToastUtils.shortShow("已切换到测试环境，请重新打开APP");
                                    break;
                                case 2:
                                    Environment.setEnv(Environment.ENV_PRODUCT);
                                    ToastUtils.shortShow("已切换到正式环境，请重新打开APP");
                                    break;
                            }
                            getMainHandler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                    System.exit(0);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                }
                            }, 500);
                        }

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spSettingsEnv.setVisibility(View.VISIBLE);
        } else {
            spSettingsEnv.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {
        super.onEvent(event);
        LogUtils.i("=========", "接收到传递的事件");
    }
}
