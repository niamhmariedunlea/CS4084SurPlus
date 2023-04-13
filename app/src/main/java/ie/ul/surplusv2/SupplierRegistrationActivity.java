package ie.ul.surplusv2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SupplierRegistrationActivity extends AppCompatActivity {

    EditText inputName,inputEmail, inputPassword, inputConfirmPassword;
    Button btnRegister;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-zA-Z0-9-]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    FirebaseFirestore fStore;
    String userID;

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

        fStore=FirebaseFirestore.getInstance();


        //Checks if there is any user logged in at the time of reg
        if(mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), SupplierMainActivity.class));
            finish();
        }


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

        if (!email.matches(emailPattern)|| email.isEmpty())
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
                        //Store data before redirecting activity

                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("suppliers").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("fullName", name);
                        user.put("email", email);
                        user.put("password", password);
                        user.put("location", "");
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Profile Created!" + userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "on Failure:" + e.toString());
                            }
                        });


                        //Redirect to fragment
                        sendUserToHome();
                        Toast.makeText(SupplierRegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SupplierRegistrationActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void sendUserToHome() {
        Intent intent= new Intent(SupplierRegistrationActivity.this, SupplierMainActivity.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void login(View view){
        startActivity(new Intent(SupplierRegistrationActivity.this, LoginActivity.class));

    }

    public void supplierMainActivity(View view){
        startActivity(new Intent(SupplierRegistrationActivity.this, SupplierMainActivity.class));
    }
}