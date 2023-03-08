package com.example.sur;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView inputEmail, inputPassword;
    ImageView signinButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.signinEmail);
        inputPassword = findViewById(R.id.signinPassword);
        signinButton = findViewById(R.id.signinbuttonlogin);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(inputEmail.getText());
                password = String.valueOf(inputPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // google documentation - https://firebase.google.com/docs/auth/android/password-auth#java_3
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login Complete!", Toast.LENGTH_SHORT).show();

                                    // ****ADD IN THE PAGE AFTER LOGIN HERE*****
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }

        });

    }
}