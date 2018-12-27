package com.fingertrip.innervoicefortraining.data;

import android.util.Log;

import com.fingertrip.innervoicefortraining.data.entity.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 数据层对外实现类
 * 负责对整个App提供所需要的数据因为需要多界面去访问，为保持数据的唯一使用“单例设计模式”
 *
 * @author 李四
 */
public class DataStore {

    private static DataStore INSTANCE = new DataStore();

    private DataStore() {
    }

    public static DataStore getINSTANCE() {
        return INSTANCE;
    }

    /**
     * 判断当前用户是否登入
     *
     * @return
     */
    public User getCurrentStudent() {
        return BmobUser.getCurrentUser(User.class);
    }

    /*
     * 用户登录
     * user 要登录的用户
     * callback 数据处理的回调接口
     * */

    public void userLogin(User user, final DataCallback<User> callback) {
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    callback.onSuccess(user);
                } else {
                    callback.onFail("登录失败，请从新尝试");
                }
            }

        });
    }

    /*
     * 用户注册
     *
     * */

    public void userSingUp(User user, final DataCallback<User> callback) {
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    callback.onSuccess(user);
                } else {
                    e.getMessage();
                    callback.onFail("注册失败，请从新注册");
                }
            }
        });
    }

}
