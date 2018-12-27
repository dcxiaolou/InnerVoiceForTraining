package com.fingertrip.innervoicefortraining.home;

import android.os.Bundle;

import com.fingertrip.innervoicefortraining.R;
import com.fingertrip.innervoicefortraining.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }
}
