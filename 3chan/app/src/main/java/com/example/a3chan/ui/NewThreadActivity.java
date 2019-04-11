package com.example.a3chan.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a3chan.R;
import com.example.a3chan.data.CategoryViewModel;
import com.example.a3chan.data.ThreadViewModel;
import com.example.a3chan.retrofit.response.Category;

import java.util.List;

public class NewThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int READ_REQUEST_CODE = 9;
    ImageView ivPict;
    Button bPost, bSelectPict;
    EditText etTitle, etBody;
    Uri photoUri;
    Spinner spCat;
    ThreadViewModel threadViewModel;
    CategoryViewModel viewModel;
    List<Category> categoryList;
    Category temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thread);
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        threadViewModel = ViewModelProviders.of(this).get(ThreadViewModel.class);
        findViews();
        events();
        loadCategoryData();
    }

    private void findViews() {
        ivPict = findViewById(R.id.ivCThrPict);
        bPost = findViewById(R.id.bCThrPost);
        bSelectPict = findViewById(R.id.bCThrSelPict);
        etTitle = findViewById(R.id.etCThrTitle);
        etBody = findViewById(R.id.etCThrBody);
        spCat = findViewById(R.id.spCThCat);
    }
    private void events() {
        bSelectPict.setOnClickListener(this);
        bPost.setOnClickListener(this);
    }

    private void loadCategoryData() {
        viewModel.getListCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                categoryList = categories;
                ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(NewThreadActivity.this, android.R.layout.simple_spinner_item, categories);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCat.setAdapter(adapter);

                spCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        temp = (Category) spCat.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.bCThrPost:
                createThread();
                break;
            case R.id.bCThrSelPict:
                searchFile();
                break;
        }
    }

    private void searchFile(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Glide
                        .with(this)
                        .load(uri)
                        .override(150, 150)
                        .into(ivPict);
                photoUri = uri;
            }
        }
    }

    private void createThread() {
        if(photoUri == null){
            Toast.makeText(this, "Threads must have an image", Toast.LENGTH_SHORT).show();
        } else {
            threadViewModel.createThread(photoUri, etBody.getText().toString(), temp.getId(), etTitle.getText().toString());
            finish();
        }
    }

}
