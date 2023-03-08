package com.example.sur;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText emailLogin = findViewById(R.id.email);
    EditText passwordLogin = findViewById(R.id.password);
    ImageView signInButtonLogin = findViewById(R.id.signinbuttonlogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }



}