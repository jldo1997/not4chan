package com.example.a3chan.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a3chan.R;
import com.example.a3chan.data.ThreadViewModel;
import com.example.a3chan.retrofit.response.Thread;

import java.util.List;


public class ThreadFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    MyThreadRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Thread> threadList;
    ThreadViewModel viewModel;


    public ThreadFragment() {
    }

    public static ThreadFragment newInstance(int columnCount) {
        ThreadFragment fragment = new ThreadFragment();
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
        View view = inflater.inflate(R.layout.fragment_thread_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                loadNewThreadDataRefresh();
        }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyThreadRecyclerViewAdapter(
                threadList,
                getActivity()
        );
        recyclerView.setAdapter(adapter);

        loadThreadData();

        return view;
    }

    private void loadThreadData() {
        viewModel.getListThreads().observe(getActivity(), new Observer<List<Thread>>() {
            @Override
            public void onChanged(@Nullable List<Thread> threads) {
                threadList = threads;
                adapter.setData(threadList);
            }
        });
    }

    private void loadNewThreadDataRefresh() {
        viewModel.getListThreads().observe(getActivity(), new Observer<List<Thread>>() {
            @Override
            public void onChanged(@Nullable List<Thread> threads) {
                threadList = threads;
                swipeRefreshLayout.setRefreshing(false);
                adapter.setData(threadList);
                viewModel.getListThreads().removeObserver(this);
            }
        });
    }


}
