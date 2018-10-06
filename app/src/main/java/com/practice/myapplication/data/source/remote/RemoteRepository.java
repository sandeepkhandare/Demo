package com.practice.myapplication.data.source.remote;

import android.util.Log;

import com.practice.myapplication.Constants;
import com.practice.myapplication.data.source.model.Data;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository implements Datasource {


    public RemoteRepository() {
        super();
    }

    @Override
    public void getData(String param, final ResponseCallback<Data> responseCallback) {

        RetrofitClient.getNetworkClass().getData(param).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response)
            {

                if (response.isSuccessful()) {
                    responseCallback.onSuccess(response.body());
                } else {
                    responseCallback.onFailure(new RuntimeException("Server Error"));
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                responseCallback.onFailure(t);
            }
        });
    }
}
