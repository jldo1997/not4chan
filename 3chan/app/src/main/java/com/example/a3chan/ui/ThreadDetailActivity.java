package com.example.a3chan.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.data.ThreadViewModel;
import com.example.a3chan.retrofit.response.Comment;
import com.example.a3chan.retrofit.response.Thread;

public class ThreadDetailActivity extends AppCompatActivity {

    private String threadID;
    private Thread thread;
    ThreadViewModel viewModel;
    TextView tvThreadDetailTitle, tvThreadDetailContent, tvThreadDetailResponses;
    ImageView ivThreadDetailPict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_detail);

        viewModel = ViewModelProviders.of(this).get(ThreadViewModel.class);
        getExtrasFromIntent();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SharedPreferencesManager.getSomeStringValue(Constants.TOKEN)==null){
                    startActivity(new Intent(ThreadDetailActivity.this, LoginActivity.class));
                } else {
                    Intent i = new Intent(ThreadDetailActivity.this, CreateCommentActivity.class);
                    i.putExtra("id", threadID);
                    i.putExtra("responseId", "");
                    startActivityForResult(i, 1);
                }
            }
        });
        findViews();
        getThread();

    }

    private void getExtrasFromIntent() {
        Intent i= getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
            threadID =(String) b.get("id");
            SharedPreferencesManager.setSomeStringValue(Constants.THREAD_ID, threadID);

    }

    private void findViews() {
        tvThreadDetailTitle = findViewById(R.id.tvThreadDetailTitle);
        tvThreadDetailContent = findViewById(R.id.tvThreadDetailContent);
        tvThreadDetailResponses = findViewById(R.id.tvThreadDetailResponses);
        ivThreadDetailPict = findViewById(R.id.ivThreadDetailPict);
    }

    private void getThread(){
        viewModel.retrieveOneThread(threadID);
        viewModel.oneThread.observe(this, new Observer<Thread>() {
            @Override
            public void onChanged(@Nullable Thread t) {
                thread = t;
                fillViews();
                fillList();
            }
        });
    }

    private void fillViews() {
        tvThreadDetailTitle.setText(thread.getTitle());
        tvThreadDetailContent.setText(thread.getHeaderComment().getContent());
        String concat = "";
        String space = ", ";
        for(Comment c : thread.getComments()){
           concat =  concat + c.getId().substring(c.getId().length() - 5) + space;
        }
        tvThreadDetailResponses.setText(concat);
        if(thread.getHeaderComment().getPhoto() != null)
            Glide.with(this)
                .load(thread.getHeaderComment().getPhoto().getUrl())
                .override(100, 100)
                .into(ivThreadDetailPict);
    }

    private void fillList() {
        Fragment f = new CommentsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.CommentContainer, f)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            finish();
        }

    }

}
