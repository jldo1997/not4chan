package com.example.not4chanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.not4chanapp.model.LoginResponse;
import com.example.not4chanapp.model.User;
import com.example.not4chanapp.retrofit.UtilToken;
import com.example.not4chanapp.retrofit.generator.ServiceGenerator;
import com.example.not4chanapp.retrofit.services.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginEmail, etLoginPass;
    private TextView tvLoginSignup;
    private Button bLoginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPass = findViewById(R.id.etLoginPass);
        tvLoginSignup = findViewById(R.id.tvLoginSignup);
        bLoginLogin = findViewById(R.id.bLoginLogin);


        tvLoginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });

        bLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etLoginEmail.getText().toString();
                String pass = etLoginPass.getText().toString();

                AuthService service = ServiceGenerator.createService(AuthService.class, email, pass);

                Call<LoginResponse> call = service.doLogin();
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() != 201) {
                            Log.e("RequestError", response.message());
                        } else {

                            UtilToken.setToken(LoginActivity.this, response.body().getToken());
                            User temp = response.body().getUser();
                            UtilToken.setUserData(LoginActivity.this, temp.getId(), temp.getPict(), temp.getName(), temp.getEmail(), temp.getRole());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("onFailure", t.getMessage());
                    }
                });

            }
        });
    }
}
