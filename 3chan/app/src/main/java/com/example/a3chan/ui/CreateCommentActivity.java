package com.example.a3chan.ui;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a3chan.R;
import com.example.a3chan.data.CommentViewModel;
import com.example.a3chan.retrofit.response.Comment;

public class CreateCommentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int READ_REQUEST_CODE = 8;
    ImageView ivPict;
    Button bSend, bSelectPict;
    EditText etContent;
    Uri photoUri;
    CommentViewModel viewModel;
    String threadId, responseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);
        getExtrasFromIntent();
        viewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        findViews();
        events();
    }

    private void getExtrasFromIntent() {
        Intent i= getIntent();
        Bundle b = i.getExtras();

        if(b!=null) {
            threadId = (String) b.get("id");
            responseId = (String) b.get("responseId");
        }

    }

    private void findViews() {
        ivPict = findViewById(R.id.ivCreateCommentPict);
        bSend = findViewById(R.id.bCreateCommentSend);
        bSelectPict = findViewById(R.id.bCreateCommentSelectPict);
        etContent = findViewById(R.id.etCreateCommentContent);
    }

    private void events() {
        bSend.setOnClickListener(this);
        bSelectPict.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.bCreateCommentSend:
                createComment();
                break;
            case R.id.bCreateCommentSelectPict:
                searchFile();
                break;
        }
    }

    private void createComment() {
        if(responseId.isEmpty()) {
            viewModel.createComment(photoUri, etContent.getText().toString(), threadId);
            Intent returnIntent = new Intent();
            setResult(RESULT_OK,returnIntent);
            finish();
        } else {
            viewModel.createResponseComment(photoUri, etContent.getText().toString(), threadId, responseId);
            Intent returnIntent = new Intent();
            setResult(RESULT_OK,returnIntent);
            finish();
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
}
