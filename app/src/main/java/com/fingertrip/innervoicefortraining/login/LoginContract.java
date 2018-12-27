package com.fingertrip.innervoicefortraining.login;

public interface LoginContract {
    interface View {
        //登录成功跳转界面
        void toHomeView();

        //登陆失败显示错误信息
        void showError(String message);

        //显示加载界面
        void showLoadingView();

        //隐藏加载界面
        void hideLoadingView();
    }

    //用于限定Presenter的行为
    interface Presenter {
        //用户登陆（用户名称，用户密码）
        void login(String name, String password);

        //新用户注册（用户名称，用户密码）
        void signUp(String name, String password);
    }
}
