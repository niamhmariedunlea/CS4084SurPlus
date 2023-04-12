package com.example.sur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSignIn(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.surlogo)
                        .setTheme(R.style.Theme_Sur)
                        .build(), RC_SIGN_IN);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                setContentView(R.layout.activity_landingpage);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                String name = user.getDisplayName();
                TextView messageName = findViewById(R.id.textViewName);
                messageName.setText("Hi, " + name + '!');

                System.out.println("Sign in successful! \n" +
                        "name = " + user.getDisplayName() + "\n" +
                        "email = " + user.getEmail() + "\n" +
                        "id = " + user.getUid());
                Toast.makeText(this, "Welcome back! " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

            } else { // if click the back button it will cancel
                if (response == null) {
                    System.out.println("Sign in cancelled");
                    return;
                } // or if the connection drops
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    System.out.println("No Internet Connection");
                    return;
                }
            }
        }
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


    private boolean isPressed = false;
    ImageButton lidlDanishBtn;
        public void onClicklidlbutton(){

            if(isPressed==false){

                lidlDanishBtn.setBackgroundResource(R.drawable.lidl_danish);
                isPressed=true;

            }else if(isPressed==true){

                lidlDanishBtn.setBackgroundResource(R.drawable.lidldanish_add);
                isPressed=false;
            }
        }
    }

