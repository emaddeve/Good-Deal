package com.emad.gooddeals;
import android.app.Activity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emad.gooddeals.http.Sender;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by emad on 18/02/16.
 *
 * Activity for setting up the Offer information
 */
public class TakePhoto extends Activity implements
        View.OnClickListener{
    ImageToJson imageToJson;
    ImageView imageView;
    JSONObject jsonObject;
    String encodedImage;
    Bitmap image;
    Double latitude;
    Double longitude;
    EditText offerMagasin;
    EditText offerName;
    EditText offerDisc;
    Spinner dropdown;
    GPSTracker gps;
    Button btnDatePicker;
    TextView txtDate;
    private SharedPreferences prefs;
    private int mYear, mMonth, mDay;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnDatePicker=(Button)findViewById(R.id.button);
        txtDate=(TextView)findViewById(R.id.selected_date);
        btnDatePicker.setOnClickListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        offerName = (EditText)findViewById(R.id.editTextName);
        offerDisc = (EditText)findViewById(R.id.edittextDes);
        offerMagasin=(EditText)findViewById(R.id.edittextMag);
      //  datePicker = (DatePicker)findViewById(R.id.datePicker);
        imageToJson=new ImageToJson();
         dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"clothes", "grocery", "other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        jsonObject=new JSONObject();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            image = null;
        } else {
            image = (Bitmap) extras.get("image");
            imageView.setImageBitmap(image);


        }
        gps = new GPSTracker(TakePhoto.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

             latitude = gps.getLatitude();
             longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
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
            jsonObject.put("category", dropdown.getSelectedItem().toString());
            jsonObject.put("description", offerDisc.getText().toString());
            jsonObject.put("name", offerName.getText().toString());
            jsonObject.put("longitude", longitude);
            jsonObject.put("latitude", latitude);
            jsonObject.put("imageString", encodedImage);
            jsonObject.put("magasin", offerMagasin.getText().toString());
            DecimalFormat mFormat= new DecimalFormat("00");

            mFormat.setRoundingMode(RoundingMode.DOWN);
            String date =  mFormat.format(Double.valueOf(mYear)) + "-" +  mFormat.format(Double.valueOf(mMonth+1)) + "-" +  mFormat.format(Double.valueOf(mDay)
                    )+"T00:00:00+01:00";

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.FRANCE);
           Date dateFin;
            dateFin= df.parse(date);

            txtDate.setText(date);
            offerName.setText(dateFin.toString());
            jsonObject.put("datefin", date);


            /*
            prefs = getSharedPreferences("userPrefs", 0);
            if(prefs.getString("user_token", null)==null) {
                Intent intent = new Intent(getApplicationContext(),
                        Login.class);
                startActivity(intent);
            }
*/

            Sender send = new Sender();
            send.send(jsonObject);


        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {




        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        mYear=year;
                        mMonth=monthOfYear;
                        mDay=dayOfMonth;
                        offerDisc.setText(mMonth+"/"+mDay+"/"+mYear);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}

