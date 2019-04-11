package com.example.a3chan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a3chan.R;
import com.example.a3chan.common.Constants;
import com.example.a3chan.common.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity {

    Fragment f;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    f = new ThreadFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, f)
                            .commit();
                    return true;
                case R.id.navigation_chat:
                    return true;
                case R.id.navigation_user:
                    if(SharedPreferencesManager.getSomeStringValue(Constants.TOKEN) == null){
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    } else{
                        f = new UserDetailsFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, f)
                                .commit();
                        return true;
                    }
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f = new ThreadFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, f)
                .commit();


    }

}
