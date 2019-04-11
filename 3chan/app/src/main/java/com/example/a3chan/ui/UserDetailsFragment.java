package com.example.a3chan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserDetailsFragment extends Fragment implements View.OnClickListener {

    CircleImageView ivUserPic;
    Button bUserEdit, bUserCreate;
    ImageButton bUserLogout;
    TextView tvUserEmail, tvUserUsername;

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    public static UserDetailsFragment newInstance(String param1, String param2) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_details, container, false);
        findViews(v);
        fillViews();
        events();
        return v;
    }

    private void events() {
        bUserLogout.setOnClickListener(this);
        bUserEdit.setOnClickListener(this);
        bUserCreate.setOnClickListener(this);
    }

    private void fillViews() {
        tvUserUsername.setText(SharedPreferencesManager.getSomeStringValue(Constants.USER_NAME));
        tvUserEmail.setText(SharedPreferencesManager.getSomeStringValue(Constants.USER_EMAIL));
        Glide
                .with(getActivity())
                .load(SharedPreferencesManager.getSomeStringValue(Constants.USER_PICT))
                .override(150, 150)
                .into(ivUserPic);

    }

    private void findViews(View v) {
        ivUserPic = v.findViewById(R.id.ibEditPic);
        bUserEdit = v.findViewById(R.id.bUserEdit);
        bUserLogout = v.findViewById(R.id.bUserLogOut);
        tvUserEmail = v.findViewById(R.id.tvUserEmail);
        tvUserUsername = v.findViewById(R.id.tvUserUsername);
        bUserCreate = v.findViewById(R.id.bUserCreateThread);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.bUserLogOut:
                doLogout();
                break;
            case R.id.bUserEdit:
                goToEditUser();
                break;
            case R.id.bUserCreateThread:
                goToCreateThread();
                break;
        }
    }

    private void goToEditUser() {
        startActivity(new Intent(getActivity(), UserEditActivity.class));
    }

    private void doLogout() {
        SharedPreferencesManager.cleanAllData();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
    private void goToCreateThread() {
        startActivity(new Intent(getActivity(), NewThreadActivity.class));
    }
}
