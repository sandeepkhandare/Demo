package com.practice.myapplication.ui;

import com.practice.myapplication.data.source.model.Data;
import com.practice.myapplication.data.source.remote.Datasource;


public class Presenter implements MainContract.Presenter {


    private MainContract.View mView;
    private Datasource mRemoteRepository;

    public Presenter(MainContract.View view, Datasource remoteRepository) {
        super();
        this.mView = view;
        this.mRemoteRepository = remoteRepository;
    }

    @Override
    public void getData(String param) {

        mRemoteRepository.getData(param, new Datasource.ResponseCallback<Data>() {
            @Override
            public void onSuccess(Data data) {

                if (data != null && data.getResults().size() > 0) {
                    mView.showData(data);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

                mView.showToast("Check your internet connection");
            }
        });
    }
}
