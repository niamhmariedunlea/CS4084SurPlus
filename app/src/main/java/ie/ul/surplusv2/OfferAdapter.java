package ie.ul.surplusv2;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;



public class OfferAdapter extends ArrayAdapter<offers> {
    public OfferAdapter(Context context, List<offers> object){
        super((Context) context,0, object);
        System.out.println("potential crash");
    }

    public void deleteItem(int position) {
        offers offer = getItem(position);
        String id = offer.getDocID();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentId = firebaseUser.getUid();
        DocumentReference documentReference;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        System.out.println("THIS " + id);

        DocumentReference myDocument = firebaseFirestore.collection("offers").document(currentId).collection("supplierOffers").document(id);
        myDocument.delete();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //System.out.println("potential crash 2");
        if(convertView == null){
            //System.out.println("potential crash 3");
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.offerlist,parent,false);
            //System.out.println("potential crash 4");
        }

        //System.out.println("double testin");
        TextView titleTextView = (TextView) convertView.findViewById(R.id.item_name);
        //System.out.println("progress 1");
        TextView prevTextView = (TextView) convertView.findViewById(R.id.item_currPrice);
        //System.out.println("progress 2");
        TextView currTextView = (TextView) convertView.findViewById(R.id.item_prevPrice);
        //System.out.println("progress 3");
        TextView locationTextView = (TextView) convertView.findViewById(R.id.item_location);
        //System.out.println("progress 4");

        offers offer = getItem(position);
        //System.out.println("progress 5");

        //System.out.println("name added: " + offer.getItem());
        titleTextView.setText(offer.getItem());
        //System.out.println("progress 6");
        prevTextView.setText("Previous Price: " + offer.getPrevPrice());
        //System.out.println("progress 7");
        currTextView.setText("Current Sale: " + offer.getCurrPrice());
        //System.out.println("progress 8");
        locationTextView.setText("Location: " + offer.getLocation());
        //System.out.println("progress 9");


        return convertView;
    }

}
