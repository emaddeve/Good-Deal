package com.emad.gooddeals.geolocation;

import com.emad.gooddeals.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class create an activity with a map and it mark the offer on the map.
 *
 * Created by emad on 26/03/16.
 */
public class Map extends AppCompatActivity implements OnMapReadyCallback {
    double longitude;
    double latitude;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        googleMap = mapFragment.getMap();
        Intent i = getIntent();


        longitude = i.getDoubleExtra("longitude", 0);
        latitude = i.getDoubleExtra("latitude", 0);
        Log.v("lsjdflsfj", "" + longitude + ": " + latitude);

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     */
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //  Marker myMarkerthirtyfour = map.addMarker(new MarkerOptions();

        CameraPosition camPos = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(15)
                        //.tilt(70)
                .build();
        //  CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        //  googleMap.animateCamera(camUpd3);
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camPos));
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));
    }
}







