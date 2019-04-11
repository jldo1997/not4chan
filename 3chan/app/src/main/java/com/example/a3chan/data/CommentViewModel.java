package com.example.a3chan.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.a3chan.common.Constants;
import com.example.a3chan.common.MyApp;
import com.example.a3chan.common.SharedPreferencesManager;
import com.example.a3chan.retrofit.request.RequestCreateComment;
import com.example.a3chan.retrofit.request.RequestEditUser;
import com.example.a3chan.retrofit.response.Photo;
import com.example.a3chan.retrofit.response.User;
import com.example.a3chan.ui.MainActivity;
import com.example.a3chan.ui.UserEditActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends AndroidViewModel {


    CommentRepository repository;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        repository = new CommentRepository();
    }

    public void createComment(Uri photoUri, String content, String threadId){
        if(photoUri != null){

            try {

                InputStream inputStream = MyApp.getContext().getContentResolver().openInputStream(photoUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                int cantBytes;
                byte[] buffer = new byte[1024*4];

                while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                    baos.write(buffer,0,cantBytes);
                }
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(MyApp.getContext().getContentResolver().getType(photoUri)), baos.toByteArray());

                RequestBody threadIdRB = RequestBody.create(MultipartBody.FORM, threadId);

                RequestBody contentRB = RequestBody.create(MultipartBody.FORM, content);


                MultipartBody.Part part =
                        MultipartBody.Part.createFormData("photo", "photo", requestFile);

                repository.createComment(part, threadIdRB,contentRB);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(photoUri == null){

            RequestCreateComment rcc = new RequestCreateComment(content, threadId);

            repository.createComment(rcc);
        }

    }

    public void createResponseComment(Uri photoUri, String content, String threadId, String responseId){
        if(photoUri != null){

            try {

                InputStream inputStream = MyApp.getContext().getContentResolver().openInputStream(photoUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                int cantBytes;
                byte[] buffer = new byte[1024*4];

                while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                    baos.write(buffer,0,cantBytes);
                }
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(MyApp.getContext().getContentResolver().getType(photoUri)), baos.toByteArray());

                RequestBody threadIdRB = RequestBody.create(MultipartBody.FORM, threadId);

                RequestBody contentRB = RequestBody.create(MultipartBody.FORM, content);

                RequestBody responseRB = RequestBody.create(MultipartBody.FORM, responseId);


                MultipartBody.Part part =
                        MultipartBody.Part.createFormData("photo", "photo", requestFile);

                repository.createResponseComment(part, threadIdRB,contentRB, responseRB);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(photoUri == null){

            RequestCreateComment rcc = new RequestCreateComment(responseId, content, threadId);

            repository.createComment(rcc);
        }

    }
}
