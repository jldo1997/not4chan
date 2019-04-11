package com.example.a3chan.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.firebase.FirebaseUser;
import com.example.a3chan.retrofit.AuthService;
import com.example.a3chan.retrofit.generator.ServiceGenerator;
import com.example.a3chan.retrofit.request.RequestSignup;
import com.example.a3chan.retrofit.response.ResponseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText etSignupUsername, etSignupEmail, etSignupPassword, etSignupConfirmPass;
    Button bSignupSignup;
    AuthService authService;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDataBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDataBaseReference = firebaseDatabase.getReference().child("users");

        findViews();
        retrofitInit();
        events();
    }


    private void findViews() {
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignupPass);
        etSignupConfirmPass = findViewById(R.id.etSignupConfirmPass);
        bSignupSignup = findViewById(R.id.bSignupSignup);
        progressBar = findViewById(R.id.progressBar);
    }

    private void retrofitInit() {
        authService = ServiceGenerator.createService(AuthService.class);
    }
    
    private void events(){
        bSignupSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignup();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private void writeUserDataOnDatabase() {
        FirebaseUser user = new FirebaseUser(auth.getCurrentUser().getEmail());
        usersDataBaseReference.child(auth.getCurrentUser().getUid()).setValue(user);
    }

    private void doSignup() {
        final String email = etSignupEmail.getText().toString();
        String name = etSignupUsername.getText().toString();
        final String pass = etSignupPassword.getText().toString();
        String confirm = etSignupConfirmPass.getText().toString();

        if(name.isEmpty()){
            etSignupUsername.setError("You need to enter a username");
        }else if(email.isEmpty()){
            etSignupEmail.setError("You need to enter a email");
        } else if(pass.isEmpty() || confirm.isEmpty()) {
            etSignupPassword.setError("You need to enter a password and confirm it");
        } else if(!pass.equals(confirm)){
            etSignupPassword.setError("Your passwords dont match");
        }else if(pass.length() < 6 ){
            etSignupPassword.setError("You need to enter a password with 6 ft long minimum");
        } else {
            progressBar.setVisibility(View.VISIBLE);
            RequestSignup requestSignup = new RequestSignup(email, pass, name);

            Call<ResponseAuth> call = authService.doSignup(requestSignup);
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if (response.isSuccessful()) {

                        auth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        if (task.isSuccessful()) {
                                            progressBar.setVisibility(View.GONE);
                                            writeUserDataOnDatabase();
                                        } else {
                                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        SharedPreferencesManager.setSomeStringValue(Constants.TOKEN, response.body().getToken());
                        SharedPreferencesManager.setSomeStringValue(Constants.USER_ID, response.body().getUser().getId());
                        SharedPreferencesManager.setSomeStringValue(Constants.USER_EMAIL, response.body().getUser().getEmail());
                        SharedPreferencesManager.setSomeStringValue(Constants.USER_NAME, response.body().getUser().getName());
                        SharedPreferencesManager.setSomeStringValue(Constants.USER_PICT, response.body().getUser().getPicture());
                        SharedPreferencesManager.setSomeStringValue(Constants.USER_ROLE, response.body().getUser().getRole());

                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finish();

                    } else {
                        Toast.makeText(SignupActivity.this, "Something went wrong, your email may be already registered", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Log.e("onFailure", t.getMessage());
                }
            });
        }
    }
}
