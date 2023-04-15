package ie.ul.surplusv2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class ConsumerBagFragment extends Fragment  {
    public AppCompatActivity cartClass;
    boolean isPressed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consumer_bag, container, false);
    }


    public void onCart1Click(View view) {
            isPressed = true;
            final ImageButton cart1Element = (ImageButton) cartClass.findViewById(R.id.cart1);
            cart1Element.setOnClickListener(new View.OnClickListener() {
                private Context ConsumerBagFragment;

                @Override
                public void onClick(View view) {

                    cart1Element.setSelected(!cart1Element.isPressed());

                    if ((cart1Element.isPressed() && isPressed) == true) {
                        Toast.makeText(ConsumerBagFragment, "Now Reserved - please collect ASAP", Toast.LENGTH_SHORT).show();

                    } else {
                        isPressed = false;
                    }
                }
            });

    }
}