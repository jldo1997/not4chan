package com.example.a3chan.retrofit;

import com.example.a3chan.retrofit.request.RequestCreateThread;
import com.example.a3chan.retrofit.response.ResponseContainer;
import com.example.a3chan.retrofit.response.Thread;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ThreadService {

    @GET("threads")
    Call<ResponseContainer<Thread>> getAllThreads();

    @GET("threads/{id}")
    Call<Thread> getOneThread(@Path("id")String id);

    @Multipart
    @POST("threads/adv")
    Call<ResponseBody> createThread(@Part MultipartBody.Part photo, @Part("content") RequestBody content, @Part("category") RequestBody category, @Part("title") RequestBody title);

    @DELETE("threads/{id}")
    Call<ResponseBody> deleteThread(@Path("id") String id);
}
