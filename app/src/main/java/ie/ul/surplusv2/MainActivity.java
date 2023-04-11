package ie.ul.surplusv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        navigationView = findViewById(R.id.bottom_navigation_consumer);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new ConsumerHomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.consumerHome);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.consumerHome:
                        fragment = new ConsumerHomeFragment();
                        break;

                    case R.id.consumerMap:
                        fragment = new ConsumerLocationFragment();
                        break;

                    case R.id.consumerBag:
                        fragment = new ConsumerBagFragment();
                        break;

                    case R.id.consumerProfile:
                        fragment = new ConsumerProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                return true;
            }
        });
    }
}