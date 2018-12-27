package com.fingertrip.innervoicefortraining.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayout());
        init();
        setListener();

    }

    /**
     * 加载布局文件，子类去实现
     * @return 布局文件
     */
    protected abstract int setLayout();

    /**
     * 初始化所有的属性
     * @return
     */
    protected abstract void init();

    /**
     * 设置监听器
     * @return
     */
    protected abstract void setListener();

    /**
     * 跳转到新界面
     * @param activityClass 新Activity
     */
    protected  void toNewView(Class activityClass){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activityClass);
        startActivity(intent);
    }

}
