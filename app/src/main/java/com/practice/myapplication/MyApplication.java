package com.practice.myapplication;

import android.app.Application;

import com.practice.myapplication.data.source.remote.RetrofitClient;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.createRetrofitInstance();

    }
}
