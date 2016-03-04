package com.emad.gooddeals;
import android.app.Activity;



import android.graphics.Bitmap;

import android.os.Bundle;


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
    private ImageView imageView;

    String encodedImage;
    Bitmap image;
    Double latitude;
    Double longitude;

    EditText offerName;
    EditText offerDisc;

    GPSTracker gps;
    JSONObject jsonObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        offerName = (EditText)findViewById(R.id.OfferName);
        offerDisc = (EditText)findViewById(R.id.OfferDisc);
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
        } else {
            image = (Bitmap) extras.get("image");
            imageView.setImageBitmap(image);

        }

    }

    public void sendOffer(){
        try {
            encodedImage = imageToJson.getStringFromBitmap(image);
            jsonObject.put("name",offerName.getText().toString());
            jsonObject.put("Discription",offerDisc.getText().toString());
            jsonObject.put("longitude",longitude);
            jsonObject.put("latitude",latitude);
            jsonObject.put("image",encodedImage);
            SendOffer asyncT = new SendOffer(jsonObject);
            asyncT.execute();


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }
}
