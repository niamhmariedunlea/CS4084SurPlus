package ie.ul.surplusv2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SupplierCreateFragment extends Fragment {

    ImageView inputImage;
    ImageView addInputImage;

    EditText inputCompany;
    EditText inputTitle;
    EditText inputPrevPrice;
    EditText inputCurrentPrice;
    EditText inputLocation;

    Button createAd;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String currentId = firebaseUser.getUid();

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    DocumentReference documentReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier_create, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        inputImage = getActivity().findViewById(R.id.imageView);
        addInputImage = getActivity().findViewById(R.id.add_image_btn);

        inputCompany = getActivity().findViewById(R.id.ad_company);
        inputTitle = getActivity().findViewById(R.id.ad_item);
        inputPrevPrice = getActivity().findViewById(R.id.ad_price_before);
        inputCurrentPrice = getActivity().findViewById(R.id.ad_price_now);
        inputLocation = getActivity().findViewById(R.id.ad_location);
        createAd = getActivity().findViewById(R.id.ad_save);

        createAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOffer();
            }
        });
    }











    private void listOffer() {

        documentReference = firebaseFirestore.collection("offers").document(currentId);

        String company = inputCompany.getText().toString();
        String item = inputTitle.getText().toString();
        String prev = inputPrevPrice.getText().toString();
        String curr = inputCurrentPrice.getText().toString();
        String loc = inputLocation.getText().toString();

        Map<String, Object> offers = new HashMap<>();
        offers.put("company", company);
        offers.put("item", item);
        offers.put("prevPrice", prev);
        offers.put("currPrice", curr);
        offers.put("location", loc);

        Log.d(TAG, "Offer Listed!");

        documentReference.set(offers).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Offer Listed!" + currentId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "on Failure:" + e.toString());
            }
        });

    }

}