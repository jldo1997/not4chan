package com.example.a3chan.retrofit;

import com.example.a3chan.retrofit.response.Category;
import com.example.a3chan.retrofit.response.ResponseContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("categories")
    Call<ResponseContainer<Category>> getAllCategories();
}
