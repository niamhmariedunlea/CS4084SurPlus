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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConsumerProfileFragment extends Fragment {

    EditText name;
    EditText email;
    EditText password;
    EditText location;
    Button saveProfile;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String currentId = firebaseUser.getUid();
    DocumentReference documentReference;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consumer_profile, container, false);
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name = getActivity().findViewById(R.id.profile_name);
        email = getActivity().findViewById(R.id.profile_email);
        password = getActivity().findViewById(R.id.profile_password);
        location = getActivity().findViewById(R.id.profile_location);
        saveProfile = getActivity().findViewById(R.id.profile_save);

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    public void onStart() {
        super.onStart();

        documentReference = firebaseFirestore.collection("consumers").document(currentId);

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists())
                        {
                            String resultName = task.getResult().getString("fullName");
                            String resultEmail = task.getResult().getString("email");
                            String resultPassword = task.getResult().getString("password");
                            String resultLocation = task.getResult().getString("location");

                            name.setText(resultName);
                            email.setText(resultEmail);
                            password.setText(resultPassword);
                            location.setText(resultLocation);

                        }else
                        {
                            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                            startActivity(intent);
                        }

                    }
                });
    }
    private void updateProfile() {

        String newName = name.getText().toString();
        String newEmail = email.getText().toString();
        String newPassword = password.getText().toString();
        String newLocation = location.getText().toString();

        final DocumentReference sDoc = firebaseFirestore.collection("consumers").document(currentId);

        sDoc
                .update("fullName", newName,
                        "email", newEmail,
                        "location", newLocation,
                        "password", newPassword)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Profile successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating Profile", e);
                    }
                });

    }
}