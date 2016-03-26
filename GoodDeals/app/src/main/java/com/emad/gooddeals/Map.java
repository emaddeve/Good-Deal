package com.emad.gooddeals;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class Map extends AppCompatActivity implements OnMapReadyCallback {
    double longitude;
    double latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent i = getIntent();
        final String offerdetails = i.getStringExtra("offerdetails");
        try {
            JSONObject jsonObject =  new JSONObject(offerdetails);
             longitude = jsonObject.getDouble("longitude");
             latitude = jsonObject.getDouble("latitude");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));
    }
}
