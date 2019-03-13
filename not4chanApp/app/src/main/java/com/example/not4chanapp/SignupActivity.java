package com.example.not4chanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.not4chanapp.model.DTO.UserDTO;
import com.example.not4chanapp.model.LoginResponse;
import com.example.not4chanapp.model.User;
import com.example.not4chanapp.retrofit.UtilToken;
import com.example.not4chanapp.retrofit.generator.ServiceGenerator;
import com.example.not4chanapp.retrofit.services.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText etSignupEmail, etSignupUsername, etSignupPass, etSignupConfirmPass;
    private Button bSignupSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupPass = findViewById(R.id.etSignupPass);
        etSignupConfirmPass = findViewById(R.id.etSignupConfirmPass);

        bSignupSignup = findViewById(R.id.bSignupSignup);

        bSignupSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etSignupEmail.getText().toString();
                String username = etSignupUsername.getText().toString();
                String pass = etSignupPass.getText().toString();
                String confirmPass = etSignupConfirmPass.getText().toString();

                if (pass.equals(confirmPass)) {

                    AuthService service = ServiceGenerator.createService(AuthService.class);

                    Call<LoginResponse> call = service.doSignup(new UserDTO(email, pass, username));
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.code() != 201) {
                                Log.e("RequestError", response.message());
                            } else {
                                UtilToken.setToken(SignupActivity.this, response.body().getToken());
                                User temp = response.body().getUser();
                                UtilToken.setUserData(SignupActivity.this, temp.getId(), temp.getPict(), temp.getName(), temp.getEmail(), temp.getRole());
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.e("onFailure", t.getMessage());
                        }
                    });


                } else {
                    Toast.makeText(SignupActivity.this, "Passwords dont match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
