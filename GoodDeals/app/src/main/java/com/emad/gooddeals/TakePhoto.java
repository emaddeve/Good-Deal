package com.emad.gooddeals;
import android.app.Activity;


import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by emad on 18/02/16.
 *
 * Activity for setting up the Offer information
 */
public class TakePhoto extends Activity {
    ImageToJson imageToJson;
    ImageView imageView;
    JSONObject jsonObject;
    String encodedImage;
    Bitmap image;
    Double latitude;
    Double longitude;

    EditText offerName;
    EditText offerDisc;

    GPSTracker gps;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        offerName = (EditText)findViewById(R.id.OfferName);
        offerDisc = (EditText)findViewById(R.id.OfferDisc);
        imageToJson=new ImageToJson();
        jsonObject=new JSONObject();
        gps = new GPSTracker(this);
        getLocation();


    }

    /**
     * method for getting the location using the class GPSTracker
     */
    public void getLocation(){
        if (gps.canGetLocation()) {
             latitude = gps.getLatitude();
             longitude = gps.getLongitude();
            setField();

            Toast.makeText(this, "Your Location is - \nLat: " +  latitude +  "\nLong: " +  longitude, Toast.LENGTH_LONG).show();

        }
    }

    /**
     * Method for setting the fields values
     */
    public void setField() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            image = null;
        } else {
            image = (Bitmap) extras.get("image");
            imageView.setImageBitmap(image);


        }

    }

    /**
     * Method for creating a jsonobject with proper information and execute asynct
     */
    public void sendOffer(View view){
        try {
            encodedImage = imageToJson.getStringFromBitmap(image);
            jsonObject.put("name",offerName.getText().toString());
            jsonObject.put("Discription",offerDisc.getText().toString());
            jsonObject.put("longitude",longitude);
            jsonObject.put("latitude",latitude);
            jsonObject.put("image", encodedImage);

            SendOffer asyncT = new SendOffer(jsonObject);
            asyncT.execute();


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }
}

