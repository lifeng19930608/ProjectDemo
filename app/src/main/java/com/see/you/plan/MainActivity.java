package com.see.you.plan;

import android.os.Bundle;
import android.view.View;

import com.android.base.base.BaseModel;
import com.android.base.base.MvpActivity;
import com.android.base.utils.LogUtils;
import com.android.base.utils.ToastUtils;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                presenter.loadData("101310222");
                presenter.version("0.9.0", 10);
            }
        });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

//    @Override
//    public void getDataSuccess(MainModel model) {
//        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
//        String showData = weatherinfo.getCity()
//                + weatherinfo.getWD()
//                + weatherinfo.getWS() + weatherinfo.getTime();
//        TextView textView = findViewById(R.id.text);
//        textView.setText(showData);
//    }

    @Override
    public void getDataSuccess(BaseModel model) {
        LogUtils.i("=========", "2222222222" + model.status);
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtils.longShow(msg);
        LogUtils.i("=========", "akshkajhskakshkajhsk" );
    }


}
