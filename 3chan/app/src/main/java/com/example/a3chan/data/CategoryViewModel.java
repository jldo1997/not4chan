package com.example.a3chan.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.a3chan.retrofit.response.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    CategoryRepository repository;
    LiveData<List<Category>> listCategories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository= new CategoryRepository();
        listCategories = repository.getAllCategories();
    }

    public LiveData<List<Category>> getListCategories() {
        return listCategories;
    }
}
