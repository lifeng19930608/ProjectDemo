package com.see.you.plan.fragment;

import com.android.base.mvp.BaseModel;
import com.android.base.mvp.BaseView;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 13:04
 * desc    :
 * modify  :
 * version : 1.0
 */

public interface FragView extends BaseView {

    void getDataSuccess(BaseModel model);

    void getDataFail(String msg);
}
