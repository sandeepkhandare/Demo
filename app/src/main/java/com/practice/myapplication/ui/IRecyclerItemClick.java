package com.practice.myapplication.ui;

import android.view.View;


public interface IRecyclerItemClick<T>
{
     void onRecyclerItemClick(View view, int position,int v, T t);
}
