package com.example.a3chan.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
import com.example.a3chan.common.MyApp;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.data.ThreadViewModel;
import com.example.a3chan.retrofit.response.Comment;
import com.example.a3chan.retrofit.response.Thread;

import org.w3c.dom.Text;

import java.util.List;


public class MyCommentsRecyclerViewAdapter extends RecyclerView.Adapter<MyCommentsRecyclerViewAdapter.ViewHolder> {

    private List<Comment> mValues;
    private Context ctx;
    private ThreadViewModel viewModel;

    public MyCommentsRecyclerViewAdapter(List<Comment> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = ViewModelProviders.of((FragmentActivity) ctx).get(ThreadViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvId.setText(holder.mItem.getId().substring(holder.mItem.getId().length() - 5));
        holder.tvContent.setText(holder.mItem.getContent());
        if(holder.mItem.getResponseTo() != null)
            holder.tvResponses.setText(holder.mItem.getResponseTo().substring(holder.mItem.getResponseTo().length() - 5));

        if(holder.mItem.getPhoto() != null){
            Glide
                    .with(ctx)
                    .load(holder.mItem.getPhoto().getUrl())
                    .override(150, 150)
                    .into(holder.ivPict);
        }else {
            Glide
                    .with(ctx)
                    .load(R.mipmap.ic_no_image)
                    .override(150, 150)
                    .into(holder.ivPict);
        }




        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesManager.getSomeStringValue(Constants.TOKEN) != null){
                    Intent i = new Intent(ctx, CreateCommentActivity.class);
                    i.putExtra("responseId", holder.mItem.getId());
                    i.putExtra("id", SharedPreferencesManager.getSomeStringValue(Constants.THREAD_ID));
                    ctx.startActivity(i);
                }
            }
        });
    }

    public void setData(Thread thread) {
        this.mValues = thread.getComments();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivPict;
        public final TextView tvContent;
        public final TextView tvResponses;
        public final TextView tvId;
        public Comment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPict = view.findViewById(R.id.ivCommentListPict);
            tvContent = view.findViewById(R.id.tvCommentListContent);
            tvResponses = view.findViewById(R.id.tvCommentListResponses);
            tvId = view.findViewById(R.id.tvCommentListId);
        }

    }
}
