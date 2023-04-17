package ie.ul.surplusv2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SupplierHomeFragment extends Fragment {

    TextView consumerName;

    private ListView mOfferListView;
    private TextView mHeaderView;

    private OfferAdapter mAdapter;
    private ArrayList<offers> mOffersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_supplier_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        consumerName = getActivity().findViewById(R.id.textViewName);
    }

    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentId = firebaseUser.getUid();
        DocumentReference documentReference;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        documentReference = firebaseFirestore.collection("suppliers").document(currentId);


        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            String resultName = task.getResult().getString("fullName");

                            consumerName.setText("Hi " + resultName + "!");

                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            String currentId = firebaseUser.getUid();
                            DocumentReference documentReference;
                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                            CollectionReference myOffers = firebaseFirestore.collection("offers").document(currentId).collection("supplierOffers");

                            //mHeaderView = (TextView) mHeaderView.findViewById(R.id.offerHeader);
                            //mOfferListView = (ListView) mOfferListView.findViewById(R.id.offerListView);


                            System.out.println("CurrentID: " + currentId);

                            mOffersList = new ArrayList<offers>();


                            myOffers.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    System.out.println("passed complete");
                                    if (task.getResult().getDocuments() != null) {
                                        System.out.println("passed if");
                                        System.out.println("what is this: " + task.getResult());
                                        System.out.println(task.getResult().isEmpty());
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            System.out.println("passed 1");
                                            offers off = document.toObject(offers.class);
                                            System.out.println("passed 2");
                                            mOffersList.add(off);
                                            System.out.println("passed 3");
                                            System.out.println("Added Document: " + off.getCurrPrice());
                                        }
                                        System.out.println("HERE");
                                        //ListView mMissionsListView = (ListView) findViewById(R.id.missionList);
                                        ListView mOfferListView = getActivity().findViewById(R.id.offerListView);
                                        System.out.println("HERE aswell");
                                        mAdapter = new OfferAdapter( this, mOffersList);
                                        System.out.println("doesn't MAKE IT PAST ABOVE LINE.");
                                        mOfferListView.setAdapter(mAdapter);
                                    } else {
                                        Log.d("OfferActivity", "Error getting documents: ", task.getException());
                                    }
                                }
                            });

                        } else {
                            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                            startActivity(intent);
                        }

                    }
                });
    }
}

//    private void listOffer() {
//        super.onStart();
//
//
//
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String currentId = firebaseUser.getUid();
//        DocumentReference documentReference;
//        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//
//        CollectionReference myOffers = firebaseFirestore.collection("offers").document(currentId).collection("supplierOffers");
//
//        //mHeaderView = (TextView) mHeaderView.findViewById(R.id.offerHeader);
//        //mOfferListView = (ListView) mOfferListView.findViewById(R.id.offerListView);
//
//
//
//        System.out.println("CurrentID: " + currentId);
//
//        mOffersList = new ArrayList<offers>();
//
//
//        myOffers.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                System.out.println("passed complete");
//                if(task.getResult().getDocuments() != null){
//                    System.out.println("passed if");
//                    System.out.println("what is this: " + task.getResult());
//                    System.out.println(task.getResult().isEmpty());
//                    for(QueryDocumentSnapshot document : task.getResult()) {
//                        System.out.println("passed 1");
//                        offers off = document.toObject(offers.class);
//                        System.out.println("passed 2");
//                        mOffersList.add(off);
//                        System.out.println("passed 3");
//                        System.out.println("Added Document: " + off.getCurrPrice());
//                    }
//                    System.out.println("HERE");
//                    //ListView mMissionsListView = (ListView) findViewById(R.id.missionList);
//                    ListView mOfferListView = getActivity().findViewById(R.id.offerListView);
//                    System.out.println("HERE aswell");
//                    mAdapter = new OfferAdapter(this, mOffersList);
//                    mOfferListView.setAdapter(mAdapter);
//                }
//                else{
//                    Log.d("OfferActivity", "Error getting documents: ", task.getException());
//                }
//            }
//        });
//    }
//
//}