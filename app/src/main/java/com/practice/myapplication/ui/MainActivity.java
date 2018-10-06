package com.practice.myapplication.ui;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.practice.myapplication.Constants;
import com.practice.myapplication.R;
import com.practice.myapplication.data.source.model.Data;
import com.practice.myapplication.databinding.ActivityMainBinding;
import com.practice.myapplication.data.source.remote.RemoteRepository;
import com.practice.myapplication.data.source.model.Data.*;

import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    MainContract.Presenter presenter;
    RvAdapter rvAdapter;
    ActivityMainBinding mBinding;
    IRecyclerItemClick<Result> resultIRecyclerItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        createRvAdapter();
        presenter = new Presenter(this, new RemoteRepository());
        presenter.getData("10");

    }


    @Override
    public void showData(Data data) {

        Log.i(Constants.TAG, "showData()::data=" + data.getResults().size());

        if (data.getResults().size() > 0) {

            rvAdapter.setData(data.getResults());

        }
    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void createRvAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvUser.setLayoutManager(linearLayoutManager);

       /* SlideInRightAnimator slideInRightAnimator=new SlideInRightAnimator();
        slideInRightAnimator.setInterpolator(new DecelerateInterpolator());
        mBinding.rvUser.setItemAnimator(slideInRightAnimator);*/

        rvAdapter = new RvAdapter(this);
        mBinding.rvUser.setAdapter(rvAdapter);

        resultIRecyclerItemClick = new IRecyclerItemClick<Result>() {
            @Override
            public void onRecyclerItemClick(View view, final int position, int v, Result result) {


                if (v == 1) {
                    mBinding.rvUser.setItemAnimator(new SlideInRightAnimator());

                    rvAdapter.removeAt(position);


                } else if (v == 2) {
                    mBinding.rvUser.setItemAnimator(new ScaleInLeftAnimator());
                    rvAdapter.removeAt(position);
                }
                else
                {

                }

            }
        };


        rvAdapter.setItemClick(resultIRecyclerItemClick);

    }
}
