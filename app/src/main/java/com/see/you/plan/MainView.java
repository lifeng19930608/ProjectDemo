package com.see.you.plan;

import com.android.base.base.BaseModel;
import com.android.base.base.BaseView;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 13:04
 * desc    :
 * modify  :
 * version : 1.0
 */

public interface MainView extends BaseView {

    void getDataSuccess(BaseModel model);

    void getDataFail(String msg);
}
