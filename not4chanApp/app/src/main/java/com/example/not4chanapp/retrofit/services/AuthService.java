package com.example.not4chanapp.retrofit.services;

import com.example.not4chanapp.model.DTO.UserDTO;
import com.example.not4chanapp.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/auth")
    Call<LoginResponse> doLogin();

    @POST("/users")
    Call<LoginResponse> doSignup(@Body UserDTO dto);
}
