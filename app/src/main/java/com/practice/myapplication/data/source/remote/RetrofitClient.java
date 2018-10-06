package com.practice.myapplication.data.source.remote;

import com.practice.myapplication.BuildConfig;

import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements Serializable,Cloneable {

    private static Retrofit retrofit;



    public static Retrofit createRetrofitInstance()
    {

        if (retrofit == null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }

    public static APIInterface getNetworkClass()
    {
        return retrofit.create(APIInterface.class);
    }

}
