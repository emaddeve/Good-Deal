package com.emad.gooddeals.http;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.emad.gooddeals.Callback;
import com.emad.gooddeals.GPSTracker;
import com.emad.gooddeals.MainActivity;
import com.emad.gooddeals.TakePhoto;
import com.emad.gooddeals.registration.Login;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by emad on 10/03/16.
 */
public class Receiver {

    String category;
    int prefDistance;
    Context context;
    Activity activity;
    private static AsyncHttpClient client = new AsyncHttpClient();
public Receiver(String category,int prefDistance,Context context,Activity activity){
this.category= category;
    this.prefDistance=prefDistance;
    this.context=context;
    this.activity=activity;
}
private JSONArray jsonArray = new JSONArray();


    GPSTracker gps;


    public   void receiver(final Callback<JSONArray> callback) throws InterruptedException {
        double longitude=0;
        double latitude=0;

        gps = new GPSTracker(context,activity);

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
        Log.v("loca",": "+longitude+" : "+prefDistance);

        client.get("http://10.0.2.2:8080/GoodDealsws/webapi/offers?longitude="+longitude+"&latitude="+latitude+
                        "&prefDistance="+prefDistance+"&category="+category,
                new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                if(callback!=null)
                    callback.onResponse(response);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



    }

    public void friendsReceiver( String friendslist,final Callback<JSONArray> callback){
        final Login login = new Login();
        try {
            StringEntity entity = new StringEntity(friendslist);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


            client.post(context, "http://10.0.2.2:8080/GoodDealsws/webapi/offers/friends", entity, "application/json",
                    new JsonHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);

                            if(callback!=null)
                                callback.onResponse(response);


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }
                    });



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

