package com.example.a3chan.retrofit;

import com.example.a3chan.retrofit.request.RequestEditUser;
import com.example.a3chan.retrofit.request.RequestLogin;
import com.example.a3chan.retrofit.request.RequestSignup;
import com.example.a3chan.retrofit.response.ResponseAuth;
import com.example.a3chan.retrofit.response.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthService {

    @POST("auth")
    Call<ResponseAuth> doLogin();

    @POST("users")
    Call<ResponseAuth> doSignup(@Body RequestSignup requestSignup);

    @PUT("users/{id}")
    Call<User> editMe(@Path("id")String id, @Body RequestEditUser requestEditUser);


}
