package com.example.a3chan.data;

import android.util.Log;

import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.retrofit.CommentService;
import com.example.a3chan.retrofit.generator.ServiceGenerator;
import com.example.a3chan.retrofit.request.RequestCreateComment;
import com.example.a3chan.retrofit.response.Comment;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {

    CommentService service;

    public CommentRepository() {
        service = ServiceGenerator.createService(CommentService.class, SharedPreferencesManager.getSomeStringValue(Constants.TOKEN));
    }

    public void createComment(MultipartBody.Part photo, RequestBody threadIdRB, RequestBody contentRB) {

        Call<ResponseBody> call = service.createCommentAdv(photo, contentRB, threadIdRB);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failureCreateComm", t.getMessage());
            }
        });
    }

    public void createComment(RequestCreateComment rcc) {

        Call<ResponseBody> call = service.createCommentAdv(rcc);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failureCreateComm", t.getMessage());
            }
        });
    }

    public void createResponseComment(MultipartBody.Part photo, RequestBody threadIdRB, RequestBody contentRB, RequestBody responseRB) {

        Call<ResponseBody> call = service.createCommentAdv(photo, contentRB, threadIdRB, responseRB);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failureCreateComm", t.getMessage());
            }
        });
    }
}
