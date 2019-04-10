package com.example.a3chan.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.data.ThreadViewModel;
import com.example.a3chan.retrofit.response.Comment;
import com.example.a3chan.retrofit.response.Thread;

import java.util.List;


public class MyThreadRecyclerViewAdapter extends RecyclerView.Adapter<MyThreadRecyclerViewAdapter.ViewHolder> {

    private List<Thread> mValues;
    private Context ctx;
    ThreadViewModel viewModel;


    public MyThreadRecyclerViewAdapter(List<Thread> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = ViewModelProviders.of((FragmentActivity) ctx).get(ThreadViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_thread, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitle.setText(holder.mItem.getTitle());
        holder.tvFirstComment.setText(holder.mItem.getHeaderComment().getContent());
        int cont = 0;
        for(Comment c : holder.mItem.getComments()){
            cont++;
        }
        //un pequeño truco en la manga para evitar que salga uno de menos
        if(cont != 0)
            cont++;

        holder.tvNumberComments.setText(Integer.toString(cont));
        if(holder.mItem.getHeaderComment().getPhoto() != null) {
            Glide
                    .with(ctx)
                    .load(holder.mItem.getHeaderComment().getPhoto().getUrl())
                    .override(150, 150)
                    .into(holder.ivPict);
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: MOVE TO DETAILS THREAD ACTIVITY
            }
        });
    }

    public void setData(List<Thread> threadList) {
        this.mValues = threadList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues != null)
            return mValues.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView ivPict;
        public TextView tvTitle;
        public TextView tvFirstComment;
        public TextView tvNumberComments;
        public Thread mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPict = view.findViewById(R.id.ivThreadListPict);
            tvTitle = view.findViewById(R.id.tvThreadListTitle);
            tvFirstComment = view.findViewById(R.id.tvThreadListFirstComment);
            tvNumberComments = view.findViewById(R.id.tvThreadListNumberComments);

        }

    }
}
