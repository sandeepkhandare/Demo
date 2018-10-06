package com.practice.myapplication.ui;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.practice.myapplication.R;

public class DataBinder {

    @BindingAdapter(value = {"app:profile_pic", "app:profile_pic_error"}, requireAll = false)
    public static void setProfilePic(ImageView view, String url, Drawable error) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_user_placeholder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(view.getContext()).load(url)
                .apply(options)
                .into(view);

    }
}
