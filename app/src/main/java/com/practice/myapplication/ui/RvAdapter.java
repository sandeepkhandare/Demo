package com.practice.myapplication.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.practice.myapplication.BR;
import com.practice.myapplication.Constants;
import com.practice.myapplication.R;
import com.practice.myapplication.data.source.model.Data.Result;
import com.practice.myapplication.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.VHEvents> {

    private List<Result> mResults;
    private Context mContext;
    IRecyclerItemClick<Result> mEventRvItemClick;


    RvAdapter(Context context) {
        this.mContext = context;
        mResults = new ArrayList<>();
    }


    public void setData(List<Result> results) {

        Log.i(Constants.TAG, "setData()::results=" + results.size());
        if (mResults != null && mResults.size() > 0) {
            mResults.clear();
        }
        if (results != null && results.size() > 0) {
            mResults.addAll(results);
            notifyDataSetChanged();

        }
        Log.i(Constants.TAG, "setData()::mResults=" + mResults.size());

    }

    public void setItemClick(IRecyclerItemClick itemClick) {
        this.mEventRvItemClick = itemClick;
    }

    @NonNull
    @Override
    public VHEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_item, parent, false);
        return new VHEvents(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VHEvents holder, int position) {

        Result result = mResults.get(position);
/*
                Log.i(Constants.TAG,"onBindViewHolder()::Email="+result.getEmail());
*/
        holder.bind(result);
        // holder.rowItemBinding.getRoot().setOnClickListener(holder);
        holder.rowItemBinding.tvAccept.setOnClickListener(holder);
        holder.rowItemBinding.tvDecline.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }


    public class VHEvents extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final RowItemBinding rowItemBinding;

        public VHEvents(RowItemBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }

        public void bind(Object obj) {

            rowItemBinding.setVariable(BR.obj, obj);
            rowItemBinding.executePendingBindings();


        }

        @Override
        public void onClick(View view) {


            if (mEventRvItemClick != null) {
                if (view == rowItemBinding.tvAccept) {
                    mEventRvItemClick.onRecyclerItemClick(view.getRootView(), getAdapterPosition(), 1, mResults.get(getAdapterPosition()));

                } else if (view == rowItemBinding.tvDecline) {
                    mEventRvItemClick.onRecyclerItemClick(view, getAdapterPosition(), 2, mResults.get(getAdapterPosition()));

                }
                else
                {
                    mEventRvItemClick.onRecyclerItemClick(view, getAdapterPosition(), 0, mResults.get(getAdapterPosition()));

                }
            }
        }


    }


    public void removeAt(int position) {
        mResults.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mResults.size());
    }


}
