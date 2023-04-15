package ie.ul.surplusv2;

import static android.content.ContentValues.TAG;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ConsumerLocationFragment extends Fragment implements OnMapReadyCallback {

    Button currentLocBtn;
    SearchView searchBar;

    MapView mMapView;
    GoogleMap mGoogleMap;
    View mView;

    Marker marker;

    FusedLocationProviderClient client;

    public ConsumerLocationFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkPerm();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_consumer_location, container, false);


        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        currentLocBtn = getActivity().findViewById(R.id.currentlocbtn);
        searchBar = getActivity().findViewById(R.id.search_box);

        currentLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Getting your Location...", Toast.LENGTH_SHORT).show();
                getCurrentLocation();
            }
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getActivity(), "Searching...", Toast.LENGTH_SHORT).show();
                searchLocationResult();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { return false;}
        });

    }


    @Override
    public void onMapReady(@NonNull GoogleMap gMap) {

        mGoogleMap = gMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
       // mGoogleMap.setMyLocationEnabled(true);


        /*

        mGoogleMap.addMarker(new MarkerOptions().position(latLng));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53, -8), 7));
        LatLng latLng = new LatLng(53, -8);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53, -8), 7));
         */

    }

    private void checkPerm() {

    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void getCurrentLocation() {
        client.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                Location location = task.getResult();
                showCurrentLocation(location.getLatitude(), location.getLongitude());


            }
        });
    }

    private void showCurrentLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mGoogleMap.moveCamera(cameraUpdate);
        mGoogleMap.animateCamera(cameraUpdate);
        marker = mGoogleMap.addMarker(markerOptions);
    }

    private void searchLocationResult() {
        String loc = searchBar.getQuery().toString();
        if (loc == null) {
            Toast.makeText(getActivity(), "Location Not Found", Toast.LENGTH_SHORT).show();
        } else {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocationName(loc, 1);
                if (addressList.size() > 0) {
                    LatLng latlng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                    if (marker != null) {
                        marker.remove();
                    }
                    MarkerOptions markerOptions = new MarkerOptions().position(latlng).title(loc);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 15);
                    mGoogleMap.animateCamera(cameraUpdate);
                    marker = mGoogleMap.addMarker(markerOptions);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}