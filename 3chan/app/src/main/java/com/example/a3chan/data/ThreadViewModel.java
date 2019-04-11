package com.example.a3chan.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.a3chan.common.MyApp;
import com.example.a3chan.retrofit.request.RequestCreateComment;
import com.example.a3chan.retrofit.response.Thread;
import com.example.a3chan.ui.MainActivity;
import com.example.a3chan.ui.ThreadDetailActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ThreadViewModel extends AndroidViewModel {

    ThreadRepository repository;
    LiveData<List<Thread>> listThreads;
    public LiveData<Thread> oneThread;

    public ThreadViewModel(@NonNull Application application) {
        super(application);
        repository = new ThreadRepository();
        listThreads = repository.getAllThreads();
        oneThread = repository.getOneThread();
    }

    public void retrieveOneThread(String id){
        repository.getOneThreadFromCall(id);
    }


    public LiveData<List<Thread>> getListThreads() {
        return listThreads;
    }
    public LiveData<Thread> getOneThread() {
        oneThread = repository.getOneThread();
        return oneThread;
    }

    public void createThread(Uri photoUri, String content, String category, String title) {
        if(photoUri != null){

            try {

                InputStream inputStream = MyApp.getContext().getContentResolver().openInputStream(photoUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                int cantBytes;
                byte[] buffer = new byte[1024*4];

                while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                    baos.write(buffer,0,cantBytes);
                }
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(MyApp.getContext().getContentResolver().getType(photoUri)), baos.toByteArray());

                RequestBody categoryRB = RequestBody.create(MultipartBody.FORM, category);

                RequestBody contentRB = RequestBody.create(MultipartBody.FORM, content);

                RequestBody titleRB = RequestBody.create(MultipartBody.FORM, title);


                MultipartBody.Part part =
                        MultipartBody.Part.createFormData("photo", "photo", requestFile);

                repository.createThread(part, categoryRB, contentRB, titleRB);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
