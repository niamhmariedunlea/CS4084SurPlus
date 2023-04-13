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

public class SupplierRegistrationActivity extends AppCompatActivity {

    EditText inputName,inputEmail, inputPassword, inputConfirmPassword;
    Button btnRegister;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-zA-Z0-9-]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_registration);

        inputName = findViewById(R.id.editText);
        inputEmail = findViewById(R.id.editText3);
        inputPassword = findViewById(R.id.editText2);
        inputConfirmPassword = findViewById(R.id.editText4);
        btnRegister=findViewById(R.id.buttonReg);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {

        String name=inputName.getText().toString();
        String email=inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (!email.matches(emailPattern))
        {
            inputEmail.setError("Please Enter a Valid Email!");
        } else if (password.isEmpty() || password.length() < 6)
        {
            inputPassword.setError("Enter a Valid Password");
        }else if(!password.equals(confirmPassword))
        {
            inputConfirmPassword.setError("Password not matching");
        }else
        {
            progressDialog.setMessage("Please wait while we are getting you set up...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToHome();
                        Toast.makeText(SupplierRegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SupplierRegistrationActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void sendUserToHome() {
        Intent intent= new Intent(SupplierRegistrationActivity.this, MainActivity.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void login(View view){
        startActivity(new Intent(SupplierRegistrationActivity.this, LoginActivity.class));

    }

    public void mainActivity(View view){
        startActivity(new Intent(SupplierRegistrationActivity.this, MainActivity.class));
    }
}