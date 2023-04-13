package ie.ul.surplusv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SupplierMainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_main);


        navigationView = findViewById(R.id.bottom_navigation_supplier);
        getSupportFragmentManager().beginTransaction().replace(R.id.supplier_body_container, new SupplierHomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.supplierHome);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.supplierHome:
                        fragment = new SupplierHomeFragment();
                        break;

                    case R.id.supplierCreate:
                        fragment = new SupplierCreateFragment();
                        break;

                    case R.id.supplierStore:
                        fragment = new SupplierStoreFragment();
                        break;

                    case R.id.supplierProfile:
                        fragment = new SupplierProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.supplier_body_container, fragment).commit();

                return true;
            }
        });
    }
    public void onImageViewLockeBurger(View view) {
        // Start the activity for the page you want to navigate to
        Intent intentLocke = new Intent(this, lockeburger_page.class);
        startActivity(intentLocke);
    }

    public void onImageViewWagamama(View view) {
        // Start the activity for the page you want to navigate to
        Intent intentWagamama = new Intent(this, wagamama_page.class);
        startActivity(intentWagamama);
    }

    public void onImageViewLidl(View view) {
        // Start the activity for the page you want to navigate to
        Intent intentLidl = new Intent(this, lidl_page.class);
        startActivity(intentLidl);
    }

    public void onFavouriteSuppliersButton(View view) {
        // Start the activity for the page you want to navigate to
        Intent intentFavourites = new Intent(this, favourite_suppliers.class);
        startActivity(intentFavourites);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        finish();
    }
}