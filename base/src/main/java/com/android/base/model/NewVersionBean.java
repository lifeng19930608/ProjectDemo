package com.android.base.model;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 15:38
 * desc    :
 * modify  :
 * version : 1.0
 */

public class NewVersionBean {
    /**
     * code : 0
     * size : 0
     * type : 0
     * updateMsg : string
     * versionNo : string
     * versionUrl : string
     */

    public int code;
    public int size;
    public int type;
    public String updateMsg;
    public String versionNo;
    public String versionUrl;


    @Override
    public String toString() {
        return "NewVersionBean{" +
                "code=" + code +
                ", size=" + size +
                ", type=" + type +
                ", updateMsg='" + updateMsg + '\'' +
                ", versionNo='" + versionNo + '\'' +
                ", versionUrl='" + versionUrl + '\'' +
                '}';
    }
}
