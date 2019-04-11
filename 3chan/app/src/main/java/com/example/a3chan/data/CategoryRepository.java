package com.example.a3chan.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.retrofit.CategoryService;
import com.example.a3chan.retrofit.generator.ServiceGenerator;
import com.example.a3chan.retrofit.response.Category;
import com.example.a3chan.retrofit.response.ResponseContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {

    CategoryService service;
    MutableLiveData<List<Category>> allCategories;

    public CategoryRepository () {
       allCategories =  getAllCategories();
    }

    public MutableLiveData<List<Category>> getAllCategories() {
        if(allCategories == null)
            allCategories = new MutableLiveData<>();

        service = ServiceGenerator.createService(CategoryService.class, SharedPreferencesManager.getSomeStringValue(Constants.TOKEN));

        Call<ResponseContainer<Category>> call =service.getAllCategories();
        call.enqueue(new Callback<ResponseContainer<Category>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Category>> call, Response<ResponseContainer<Category>> response) {
                allCategories.setValue(response.body().getRows());
            }

            @Override
            public void onFailure(Call<ResponseContainer<Category>> call, Throwable t) {
                Log.e("failureAllCategories", t.getMessage());
            }
        });
        return allCategories;
    }
}
