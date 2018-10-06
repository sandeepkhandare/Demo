package com.practice.myapplication.data.source.remote;

import com.practice.myapplication.data.source.model.Data;

public interface Datasource {

    interface ResponseCallback<T> {
        void onSuccess(T t);

        void onFailure(Throwable throwable);
    }

    void getData(String param, ResponseCallback<Data> responseCallback);
}
