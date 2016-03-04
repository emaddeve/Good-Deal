package com.emad.gooddeals;
import android.app.Activity;



import android.graphics.Bitmap;

import android.os.Bundle;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by emad on 18/02/16.
 */
public class TakePhoto extends Activity {

    private ImageView imageView;
    TextView e;
    TextView e2;
    String encodedImage;
    Bitmap image;
    Double latitude;
    Double longitude;
    String path;
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        e = (TextView) findViewById(R.id.textView3);
        e2 = (TextView) findViewById(R.id.textView4);
        getLocation();

    }


    public void getLocation(){
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            setField();

            Toast.makeText(this, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

        }
    }

    public void setField() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {

            image = null;
            path = null;
        } else {
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");

            image = (Bitmap) extras.get("image");
            e.setText(latitude.toString());
            imageView.setImageBitmap(image);
            e2.setText(encodedImage);
            // image=loadImageFromStorage(path);


            imageView.setImageBitmap(image);

        }



    }


}

