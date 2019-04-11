package com.example.a3chan.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a3chan.R;
import com.example.a3chan.data.ThreadViewModel;
import com.example.a3chan.retrofit.response.Comment;
import com.example.a3chan.retrofit.response.Thread;

import java.util.List;

public class CommentsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    ThreadViewModel viewModel;
    List<Comment> commentList;
    MyCommentsRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    public CommentsFragment() {
    }


    public static CommentsFragment newInstance(int columnCount) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        viewModel = ViewModelProviders.of(getActivity()).get(ThreadViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyCommentsRecyclerViewAdapter(
                commentList,
                getActivity()
        );
        loadCommentData();
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void loadCommentData() {
        viewModel.getOneThread().observe(getActivity(), new Observer<Thread>() {
            @Override
            public void onChanged(@Nullable Thread thread) {
                commentList = thread.getComments();
                adapter.setData(thread);
            }
        });
    }



}
