package com.see.you.plan.test;

import com.android.base.mvp.BaseModel;
import com.android.base.mvp.BaseView;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 13:04
 * desc    :
 * modify  :
 * version : 1.0
 */

public interface MainView extends BaseView {

    <T> void getDataSuccess(BaseModel model, T t);

    void getDataFail(String msg);
}
