package com.example.not4chanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.not4chanapp.listeners.UserInteractionListener;
import com.example.not4chanapp.retrofit.UtilToken;

public class MainActivity extends AppCompatActivity implements UserInteractionListener {

    private Fragment fUser;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_chat:
                    return true;
                case R.id.navigation_user:
                    if(UtilToken.getToken(MainActivity.this) == null){
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    } else{
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, fUser, "user")
                                .commit();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fUser = new UserFragment();
    }

}
