package com.fingertrip.innervoicefortraining.splash;

import com.fingertrip.innervoicefortraining.data.DataStore;
import com.fingertrip.innervoicefortraining.utils.Constants;
import com.fingertrip.innervoicefortraining.utils.SharedPreferencesUtils;

/**
 * 中间件，完成SplashActivity与数据层之间的交互
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View rootView;
    private DataStore mDataStore;
    public SplashPresenter(SplashContract.View rootView){
        this.rootView = rootView;
        mDataStore = DataStore.getINSTANCE();
    }
    @Override
    public void isFirstRun() {
        if(SharedPreferencesUtils.getBoolean(Constants.SP_KEY_FIRST_RUN)){
            //是否登入
            isLogin();
        } else {
            rootView.toGuideView();
        }
    }

    @Override
    public void isLogin() {
        if(mDataStore.getCurrentStudent() != null){
            rootView.toHomeView();
        }else {
            rootView.toLoginView();
        }
    }
}
