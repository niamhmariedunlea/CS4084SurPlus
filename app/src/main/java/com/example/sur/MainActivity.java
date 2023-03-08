package com.example.sur;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
    }

    public void onClickSignIn(View view) {
        setContentView(R.layout.activity_login);
    }

    public void onClickRegisterConsumer(View view) {
        setContentView(R.layout.activity_register_consumer);
    }

}

