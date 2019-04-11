package com.example.a3chan.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.retrofit.ThreadService;
import com.example.a3chan.retrofit.generator.ServiceGenerator;
import com.example.a3chan.retrofit.request.RequestCreateThread;
import com.example.a3chan.retrofit.response.ResponseContainer;
import com.example.a3chan.retrofit.response.Thread;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThreadRepository {

    ThreadService service;
    MutableLiveData<List<Thread>> allThreads;
    MutableLiveData<Thread> oneThread;

    ThreadRepository() {
        allThreads = getAllThreads();
    }

    public MutableLiveData<List<Thread>> getAllThreads() {
        if(allThreads == null)
            allThreads = new MutableLiveData<>();
        service = ServiceGenerator.createService(ThreadService.class);
        Call<ResponseContainer<Thread>> call = service.getAllThreads();

        call.enqueue(new Callback<ResponseContainer<Thread>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Thread>> call, Response<ResponseContainer<Thread>> response) {
                if(response.isSuccessful()) {
                    allThreads.setValue(response.body().getRows());
                } else {
                    Log.d("another Error", "this is bigger and badder");
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Thread>> call, Throwable t) {
                Log.e("failureAllThreads", t.getMessage());
            }
        });
        return allThreads;
    }

    public void getOneThreadFromCall(String id) {
        if(oneThread == null)
            oneThread = new MutableLiveData<>();

        service = ServiceGenerator.createService(ThreadService.class);
        Call<Thread> call = service.getOneThread(id);
        call.enqueue(new Callback<Thread>() {
            @Override
            public void onResponse(Call<Thread> call, Response<Thread> response) {
                if(response.isSuccessful()) {
                    oneThread.setValue(response.body());
                } else {
                    Log.d("another Error", "this is bigger and badder");
                }
            }

            @Override
            public void onFailure(Call<Thread> call, Throwable t) {
                Log.e("failureOneThreads", t.getMessage());
            }
        });

    }

    public MutableLiveData<Thread> getOneThread() {
        if(oneThread == null)
            oneThread = new MutableLiveData<>();

        return oneThread;
    }

    public void createThread(MultipartBody.Part photo, RequestBody categoryRB, RequestBody contentRB, RequestBody titleRB) {
        ThreadService service2 = ServiceGenerator.createService(ThreadService.class, SharedPreferencesManager.getSomeStringValue(Constants.TOKEN));

        Call<ResponseBody> call = service2.createThread(photo,contentRB, categoryRB, titleRB);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failureCreateThread", t.getMessage());
            }
        });

    }

}
