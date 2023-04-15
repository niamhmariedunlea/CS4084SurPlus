package ie.ul.surplusv2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import kotlinx.coroutines.ObsoleteCoroutinesApi;


public class ConsumerBagFragment extends Fragment {

    CoordinatorLayout coordinatorLayout;
    boolean isPressed = false;

    public ConsumerBagFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consumer_bag, container, false);


    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ConsumerBagFragment", "onActivityCreated called");
        coordinatorLayout = getActivity().findViewById(R.id.scrollView2);
        ImageButton cart1element = getActivity().findViewById(R.id.cart1);

        cart1element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickElement1(cart1element);
            }
        });
    }

            private void clickElement1(ImageButton cart1element) {
                Log.d("ConsumerBagFragment", "clickElement1 called");
        cart1element.setEnabled(false);
                // Toast.makeText(th "Now Reserved - please collect ASAP", Toast.LENGTH_SHORT).show();

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Now Reserved - please collect ASAP", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }


