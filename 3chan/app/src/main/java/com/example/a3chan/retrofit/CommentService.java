package com.example.a3chan.retrofit;

import com.example.a3chan.retrofit.request.RequestCreateComment;
import com.example.a3chan.retrofit.response.Comment;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CommentService {

    @POST("comments")
    Call<Comment> createComment(@Body RequestCreateComment rcc);

    @Multipart
    @POST("comments/adv")
    Call<ResponseBody> createCommentAdv(@Part MultipartBody.Part photo, @Part("content") RequestBody content, @Part("threadId") RequestBody threadId);

    @Multipart
    @POST("comments/adv")
    Call<ResponseBody> createCommentAdv(@Part MultipartBody.Part photo, @Part("content") RequestBody content, @Part("threadId") RequestBody threadId, @Part("responseTo") RequestBody responseTo);

    @POST("comments")
    Call<ResponseBody> createCommentAdv(@Body RequestCreateComment rcc);

}
