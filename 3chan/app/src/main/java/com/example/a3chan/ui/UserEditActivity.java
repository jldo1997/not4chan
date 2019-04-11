package com.example.a3chan.ui;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.retrofit.AuthService;
import com.example.a3chan.retrofit.PhotoService;
import com.example.a3chan.retrofit.generator.ServiceGenerator;
import com.example.a3chan.retrofit.request.RequestEditUser;
import com.example.a3chan.retrofit.response.Photo;
import com.example.a3chan.retrofit.response.ResponseAuth;
import com.example.a3chan.retrofit.response.ResponseContainer;
import com.example.a3chan.retrofit.response.User;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserEditActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int READ_REQUEST_CODE = 42; //pongo el 42 como un guiño a la guia del autopista galactico, no por que lo haya copiado
    CircleImageView ivEditPic;
    Button bEditSave, bEditPict;
    EditText etEditUsername, etEditEmail;
    private AuthService service;
    Uri photoUri;
    String newPhotoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        findViews();
        fillViews();
        generateClient();
        events();
    }

    private void generateClient() {
        service = ServiceGenerator.createService(AuthService.class, SharedPreferencesManager.getSomeStringValue(Constants.TOKEN));
    }

    private void findViews() {
        ivEditPic = findViewById(R.id.ibEditPic);
        bEditPict = findViewById(R.id.bEditPict);
        bEditSave = findViewById(R.id.bEditSave);
        etEditUsername = findViewById(R.id.etEditUsername);
        etEditEmail = findViewById(R.id.etEditEmail);
    }

    private void fillViews() {
        etEditUsername.setText(SharedPreferencesManager.getSomeStringValue(Constants.USER_NAME));
        etEditEmail.setText(SharedPreferencesManager.getSomeStringValue(Constants.USER_EMAIL));
        Glide
                .with(this)
                .load(SharedPreferencesManager.getSomeStringValue(Constants.USER_PICT))
                .override(150, 150)
                .into(ivEditPic);
    }

    private void events(){
        bEditSave.setOnClickListener(this);
        bEditPict.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.bEditSave:
                createPhotoAndUpdate();
                break;
            case R.id.bEditPict:
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

    private void createPhotoAndUpdate(){
        if (photoUri != null) {

            PhotoService photoService = ServiceGenerator.createService(PhotoService.class, SharedPreferencesManager.getSomeStringValue(Constants.TOKEN));

            try {
                InputStream inputStream = getContentResolver().openInputStream(photoUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                int cantBytes;
                byte[] buffer = new byte[1024*4];

                while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                    baos.write(buffer,0,cantBytes);
                }


                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(getContentResolver().getType(photoUri)), baos.toByteArray());


                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("photo", "photo", requestFile);

                Call<Photo> call = photoService.createPhoto(body);

                call.enqueue(new Callback<Photo>() {
                    @Override
                    public void onResponse(Call<Photo> call, Response<Photo> response) {
                        if (response.isSuccessful()) {
                            Log.d("Uploaded", "Éxito");
                            newPhotoUrl = response.body().getUrl();
                            String email = etEditEmail.getText().toString();
                            String name = etEditUsername.getText().toString();
                            String id2 = SharedPreferencesManager.getSomeStringValue(Constants.USER_ID);
                            RequestEditUser reu;
                            if(newPhotoUrl != null)
                                reu = new RequestEditUser(email, name, newPhotoUrl);
                            else
                                reu = new RequestEditUser(email, name, SharedPreferencesManager.getSomeStringValue(Constants.USER_PICT));

                            Call<User> call2 = service.editMe(id2, reu);
                            call2.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    SharedPreferencesManager.setSomeStringValue(Constants.USER_NAME, response.body().getName());
                                    SharedPreferencesManager.setSomeStringValue(Constants.USER_EMAIL, response.body().getEmail());
                                    SharedPreferencesManager.setSomeStringValue(Constants.USER_PICT, response.body().getPicture());
                                    startActivity(new Intent(UserEditActivity.this, MainActivity.class));
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.e("failureEditUser", t.getMessage());
                                }
                            });
                        } else {
                            Log.e("Upload error", response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Photo> call, Throwable t) {
                        Log.e("failureCreatePhoto", t.getMessage());
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                        .into(ivEditPic);
                photoUri = uri;
            }
        }
    }
}
