package com.fingertrip.innervoicefortraining.guide;

import com.fingertrip.innervoicefortraining.utils.Constants;
import com.fingertrip.innervoicefortraining.utils.SharedPreferencesUtils;

public class GuidePresenter implements GuideContract.Presenter{

    private GuideContract.View rootView;
    public GuidePresenter(GuideContract.View rootView){
        this.rootView = rootView;
    }
    @Override
    public void saveFirstRunState() {
        SharedPreferencesUtils.saveBoolean(Constants.SP_KEY_FIRST_RUN, true);
        rootView.toLoginView();
    }
}
