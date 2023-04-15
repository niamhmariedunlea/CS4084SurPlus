package ie.ul.surplusv2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class favourite_suppliers extends AppCompatActivity {

    boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_suppliers);

    }

    public void onClickLidlDanish(View view) {
        final ImageButton danishLidl = (ImageButton) findViewById(R.id.LidlimageButton);
        danishLidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danishLidl.setSelected(!danishLidl.isPressed());

                if((danishLidl.isPressed() && isPressed) == true){
                    openCart(danishLidl);

                }
                if (danishLidl.isPressed()) {
                    danishLidl.setImageResource(R.drawable.lidldanish_add);
                    isPressed = true;
                }
                else {
                    danishLidl.setImageResource(R.drawable.lidl_danish);
                }
            }
        });
    }



    private void openCart(ImageButton danishLidl) {
        setContentView(R.layout.fragment_consumer_bag);
        final ImageButton cart1 = (ImageButton) findViewById(R.id.cart1);
        cart1.setImageResource(R.drawable.lidl_danish);
    }
}
