package com.example.a3chan.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.retrofit.AuthService;
import com.example.a3chan.retrofit.generator.ServiceGenerator;
import com.example.a3chan.retrofit.request.RequestLogin;
import com.example.a3chan.retrofit.response.ResponseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button bLoginLogin;
    TextView tvLoginSignup;
    EditText etLoginEmail, etLoginPass;
    AuthService authService;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        findViews();
        events();
    }

    private void findViews() {
        bLoginLogin = findViewById(R.id.bLoginLogin);
        tvLoginSignup = findViewById(R.id.tvLoginSignup);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPass = findViewById(R.id.etLoginPass);
    }


    private void events () {
        bLoginLogin.setOnClickListener(this);
        tvLoginSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.bLoginLogin:
                doLogin();
                break;
            case R.id.tvLoginSignup:
                goToSignup();
                break;
        }
    }

    private void doLogin() {

        final String email = etLoginEmail.getText().toString();
        final String pass = etLoginPass.getText().toString();

        if (email.isEmpty()) {
            etLoginEmail.setError("You need to have a registered email");
        } else if (pass.isEmpty()) {
            etLoginPass.setError("You need to have a registered password");
        } else {
            authService = ServiceGenerator.createService(AuthService.class, email, pass);

            Call<ResponseAuth> call = authService.doLogin();

            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if (response.isSuccessful()) {

                        if(response.body().getUser().getRole().matches("banned")){
                            Toast.makeText(LoginActivity.this, "Your user has been banned, for some reason you sure know ;D", Toast.LENGTH_SHORT).show();

                        } else {

                            auth.signInWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()) {
                                                Toast.makeText(LoginActivity.this, "login failed." + task.getException(),
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(LoginActivity.this, "Authentication OK." ,
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

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Something went wrong, check access data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Log.d("onFailure", t.getMessage());
                }
            });
        }
    }

    private void goToSignup() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }
}
