package com.fingertrip.innervoicefortraining.data;

//数据处理的回调接口

public interface DataCallback<D> {

    //数据获取成功
    void onSuccess(D data);

    //数据获取失败
    void onFail(String message);

}
