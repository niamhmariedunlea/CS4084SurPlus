package ie.ul.surplusv2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SupplierCreateFragment extends Fragment {

    final int CAM_REQ = 101;
    final int CAM_OPEN_REQ = 102;

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

        addInputImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermission();
                //takePhoto();

            }
        });

        createAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOffer();
                Toast.makeText(getActivity(), "Offer has been listed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[] {android.Manifest.permission.CAMERA}, CAM_REQ);
        }
        else
        {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAM_REQ)
        {
            //User allowed camera perms
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                openCamera();
            }
            else
            {
                Toast.makeText(getActivity(), "Camera Permissions required!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAM_OPEN_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAM_OPEN_REQ)
        {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            inputImage.setImageBitmap(image);
        }
    }

    private void takePhoto() {

    }


    private void listOffer() {

        documentReference = firebaseFirestore.collection("offers").document(currentId).collection("supplierOffers").document();

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