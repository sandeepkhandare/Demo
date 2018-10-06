package com.practice.myapplication.data.source.remote;



import com.practice.myapplication.data.source.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {


    @GET("api/?")
    Call<Data> getData(@Query("results") String param);

}
