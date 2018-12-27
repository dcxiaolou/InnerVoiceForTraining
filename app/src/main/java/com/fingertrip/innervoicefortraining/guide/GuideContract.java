package com.fingertrip.innervoicefortraining.guide;

public interface GuideContract {
    /**
     * 用于限定View的行为
     */
    interface View{
        /**
         * 跳转登入界面
         */
        void toLoginView();
    }

    /**
     * 用于限定Presenter的行为
     */
    interface Presenter{
        /**
         * 保存第一次启动的状态
         */
        void saveFirstRunState();
    }
}
