package com.practice.myapplication.ui;

import com.practice.myapplication.data.source.model.Data;

public interface MainContract {

    interface View
    {
        void showData(Data data);
        void showToast(String  msg);

    }

    interface Presenter
    {

        void getData(String param);
    }
}
