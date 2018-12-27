package com.fingertrip.innervoicefortraining;
/*1. Application的使用
        多个组件之间数据共享
        举例：两个Activity之间数据共享
        Application 对同一个应用程序是唯一的，所以可以使用Application进行数据共享*/
import android.app.Application;

import com.fingertrip.innervoicefortraining.utils.SharedPreferencesUtils;

import cn.bmob.v3.Bmob;

public class InnerVoiceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //第一：默认初始化
        Bmob.initialize(this, "63994fa3844e6538507f320792525014");

        SharedPreferencesUtils.init(this);

    }
}
