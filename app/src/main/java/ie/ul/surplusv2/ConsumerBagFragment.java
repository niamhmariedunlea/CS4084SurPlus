package ie.ul.surplusv2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kotlinx.coroutines.ObsoleteCoroutinesApi;


public class ConsumerBagFragment extends Fragment {
    public AppCompatActivity cartClass;
    private Context ConsumerBagFragment;

    boolean isPressed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consumer_bag, container, false);


    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ImageButton cart1element = getActivity().findViewById(R.id.cart1);

        cart1element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickElement1(cart1element);
            }

            private void clickElement1(ImageButton cart1element) {
                cart1element.setEnabled(false);
                Toast.makeText(ConsumerBagFragment, "Now Reserved - please collect ASAP", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


