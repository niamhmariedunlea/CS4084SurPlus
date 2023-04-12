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

public class RegisterConsumer extends AppCompatActivity {

    TextView inputName, inputEmail, inputPassword1, inputPassword2;
    ImageView registerButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_consumer);
        mAuth = FirebaseAuth.getInstance();

        inputName = findViewById(R.id.registerName);
        inputEmail = findViewById(R.id.registerEmail);
        inputPassword1 = findViewById(R.id.registerPassword1);
        inputPassword2 = findViewById(R.id.registerPassword2);
        registerButton = findViewById(R.id.signupimagebutton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(inputEmail.getText());
                password = String.valueOf(inputPassword1.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterConsumer.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterConsumer.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterConsumer.this, "Account Creation Complete!", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterConsumer.this, "Failed to log in", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });




    }
}