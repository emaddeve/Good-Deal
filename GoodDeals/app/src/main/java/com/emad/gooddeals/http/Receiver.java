package com.emad.gooddeals.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.emad.gooddeals.GPSTracker;
import com.emad.gooddeals.MainActivity;
import com.emad.gooddeals.TakePhoto;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by emad on 10/03/16.
 */
public class Receiver {

    String category;
    int prefDistance;
    Context context;
public Receiver(String category,int prefDistance,Context context){
this.category= category;
    this.prefDistance=prefDistance;
    this.context=context;
}
private JSONArray jsonArray = new JSONArray();


    GPSTracker gps;


    public   void receiver() throws InterruptedException {
        double longitude=0;
        double latitude=0;
        gps = new GPSTracker(context);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:8080/GoodDealsws/webapi/offers?longitude="+longitude+"&latitude="+latitude+
                        "&prefDistance="+prefDistance+"&category="+category,
                new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                jsonArray = response;
                MainActivity m = new MainActivity();
                m.setlistview(jsonArray);
                Log.v("reciver", response.toString());



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



    }
}

