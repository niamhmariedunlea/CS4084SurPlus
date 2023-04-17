package ie.ul.surplusv2;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;



public class OfferAdapter extends ArrayAdapter<offers> {
    public OfferAdapter(FragmentActivity context, List<offers> object){
        super((Context) context,0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_supplier_home,parent,false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.item_name);
        TextView prevTextView = (TextView) convertView.findViewById(R.id.item_currPrice);
        TextView currTextView = (TextView) convertView.findViewById(R.id.item_prevPrice);
        TextView locationTextView = (TextView) convertView.findViewById(R.id.item_location);

        offers offer = getItem(position);

        titleTextView.setText(offer.getName());
        prevTextView.setText(offer.getPrevPrice());
        currTextView.setText(offer.getCurrPrice());
        locationTextView.setText(offer.getLocation());


        return convertView;
    }

}
