package com.emad.gooddeals;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


/**
 * Created by emad on 18/02/16.
 */
public class TakePhoto extends Activity {
    ImageToJson imageToJson = new ImageToJson();

    Bitmap photo;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private ImageView imageView2;
    Bitmap bitmappic;
    JSONObject jsonObject;
    TextView e;
    TextView e2;
    String encodedImage;
    Bitmap image;
    Double latitude;
    Double longitude;

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        e = (TextView) findViewById(R.id.textView3);
        e = (TextView) findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                latitude = null;
                longitude = null;
                image = null;
            } else {
                latitude = extras.getDouble("latitude");
                longitude = extras.getDouble("longitude");
                image = (Bitmap) extras.get("image");
                e.setText(latitude.toString());
                e2.setText((longitude.toString()));
                imageView.setImageBitmap(image);

            }


        }
    }
}
/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
             photo = (Bitmap) data.getExtras().get("data");

           // imageView.setImageBitmap(photo);

                 encodedImage= imageToJson.getStringFromBitmap(photo);
                e.setText(encodedImage);
        try {
            jsonObject = new JSONObject("{\"image\":\"" + encodedImage +"\"}");
            String jsonString = jsonObject.getString("image");
            Bitmap m = imageToJson.getBitmapFromString(jsonString);
            imageView.setImageBitmap(m);
        }catch(JSONException e){}
        }
    }
    */





