package com.example.a3chan.retrofit;

import com.example.a3chan.retrofit.response.Photo;
import com.example.a3chan.retrofit.response.ResponseAuth;
import com.example.a3chan.retrofit.response.ResponseContainer;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PhotoService {

    @GET("photos")
    Call<ResponseContainer<Photo>> getAllPhotos();

    @Multipart
    @POST("photos")
    Call<Photo> createPhoto(@Part MultipartBody.Part photo);

}
