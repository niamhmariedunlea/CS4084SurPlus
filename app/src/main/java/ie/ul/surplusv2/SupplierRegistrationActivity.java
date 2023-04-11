package ie.ul.surplusv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SupplierRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_registration);
    }


    public void login(View view){
        startActivity(new Intent(SupplierRegistrationActivity.this, LoginActivity.class));

    }

    public void mainActivity(View view){
        startActivity(new Intent(SupplierRegistrationActivity.this, MainActivity.class));
    }
}