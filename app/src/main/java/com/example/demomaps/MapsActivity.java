package com.example.demomaps;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Address> addresses = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder gcd = new Geocoder(this, Locale.getDefault());

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(23.033863, 72.585022);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Ahmedabad"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                System.out.println(point.latitude+"---"+ point.longitude);
                Geocoder gcd = new Geocoder(MapsActivity.this, Locale.getDefault());

                try {
                    addresses = gcd.getFromLocation(point.latitude, point.longitude, 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        MarkerOptions marker = new MarkerOptions().position(new LatLng(point.latitude, point.longitude));
                        marker.title(addresses.get(0).getLocality());
                        mMap.addMarker(marker);
                        //marker.position(point).title(addresses.get(0).getLocality());
                    }
                    else {
                        // do your stuff
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}