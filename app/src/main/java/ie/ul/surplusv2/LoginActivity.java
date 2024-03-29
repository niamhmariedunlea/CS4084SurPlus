package ie.ul.surplusv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    Button btnLogin;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-zA-Z0-9-]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.editText3);
        inputPassword = findViewById(R.id.editText2);
        btnLogin=findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformLogin();
            }
        });
    }

    private void PerformLogin() {

        String email=inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        //Some validation Checks
        if (!email.matches(emailPattern) || email.isEmpty())
        {
            inputEmail.setError("Please Enter a Valid Email!");
        } else if (password.isEmpty() || password.length() < 6)
        {
            inputPassword.setError("Enter a Valid Password");
        } else {
            progressDialog.setMessage("Login in-progress...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            //Here is the actual signin process with Firebase
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        //Redirects the person to home page - method below
                        sendUserToHome(email);
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }

    //Depending on their email - whether it's personal or company -  they'll be redirected to Consumer or Supplier Home Page
    private void sendUserToHome(String email) {
        if (email.matches("^[\\w.+\\-]+@gmail\\.com$") || email.matches("^[\\w.+\\-]+@surplus\\.com$"))
        {
            Intent intent= new Intent(LoginActivity.this, MainActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            Intent intent= new Intent(LoginActivity.this, SupplierMainActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }


    public void register(View view){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void registerSupplier(View view){
        startActivity(new Intent(LoginActivity.this, SupplierRegistrationActivity.class));
    }
}