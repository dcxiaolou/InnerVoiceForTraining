package com.fingertrip.innervoicefortraining.login;

import android.util.Log;

import com.fingertrip.innervoicefortraining.data.DataCallback;
import com.fingertrip.innervoicefortraining.data.DataStore;
import com.fingertrip.innervoicefortraining.data.entity.User;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View rootView;
    private DataStore mDataStore;

    public LoginPresenter(LoginContract.View rootView) {
        this.rootView = rootView;
        mDataStore = DataStore.getINSTANCE();
    }

    @Override
    public void login(String name, String password) {
        //显示加载界面
        rootView.showLoadingView();
        //异步处理用户登录
        final User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        //完成登陆功能
        mDataStore.userLogin(user, new DataCallback<User>() {
            @Override
            public void onSuccess(User data) {
                //隐藏加载界面
                rootView.hideLoadingView();
                //成功跳转
                rootView.toHomeView();
            }

            @Override
            public void onFail(String message) {
                //隐藏加载界面
                rootView.hideLoadingView();
                //显示错误信息
                rootView.showError(message);
            }
        });
    }

    @Override
    public void signUp(String name, String password) {
        //显示加载界面
        rootView.showLoadingView();
        //异步处理用户登录
        final User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        Log.d("TAG", "signUp: " + name + "  " + password);
        //完成登陆功能
        mDataStore.userSingUp(user, new DataCallback<User>() {
            @Override
            public void onSuccess(User data) {
                Log.d("TAG", data.getUsername());
                //隐藏加载界面
                rootView.hideLoadingView();
                //成功跳转
                rootView.toHomeView();
            }

            @Override
            public void onFail(String message) {
                //隐藏加载界面
                rootView.hideLoadingView();
                //显示错误信息
                rootView.showError(message);
            }
        });
    }
}
