package com.example.sur;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSignIn(View view) {
        setContentView(R.layout.activity_login);
    }


    public void onClickConsumerRegister(View view) {
        setContentView(R.layout.activity_register_consumer);
    }

}

