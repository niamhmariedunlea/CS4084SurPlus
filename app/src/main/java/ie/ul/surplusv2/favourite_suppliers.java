package ie.ul.surplusv2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



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
        final ImageButton reserve = (ImageButton) findViewById(R.id.reserve);
        cart1.setImageResource(R.drawable.danish_cart_button);
        
            Log.d("ConsumerBagFragment", "onActivityCreated called");

        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem();
            }
        });

            reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickReserve();
                }
            });
        }


        private void clickItem(){
            final ImageButton cart1 = (ImageButton) findViewById(R.id.cart1);
            Log.d("ConsumerBagFragment", "clickElement1 called");
            setContentView(R.layout.fragment_consumer_bag);
            cart1.setEnabled(false);
            Toast.makeText(this, "Item has been removed from your bag", Toast.LENGTH_SHORT).show();

        }
    private void clickReserve() {
        final ImageButton cart1 = (ImageButton) findViewById(R.id.cart1);
            Log.d("ConsumerBagFragment", "clickElement1 called");
        setContentView(R.layout.fragment_consumer_bag);
        cart1.setEnabled(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Now Reserved - please collect ASAP");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  when the "OK" button is clicked
                dialog.dismiss(); // close popup
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        // setContentView(R.layout.fragment_consumer_home);
        // maybe direct to homepage??
        }
    }
    
