package com.emad.gooddeals.http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.emad.gooddeals.geolocation.GPSTracker;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Class for making requests to the service to get data
 *
 * Created by emad on 10/03/16.
 */
public class Receiver {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private String category;
    private int prefDistance;
     Context context;
     Activity activity;
    private GPSTracker gps;

    /**
     * constractor
     *
     * @param category     the category to filter the results with
     * @param prefDistance the prefered distance of the offers from the client location
     * @param context      context used
     * @param activity     antivity used
     */
    public Receiver(String category, int prefDistance, Context context, Activity activity) {
        this.category = category;
        this.prefDistance = prefDistance;
        this.context = context;
        this.activity = activity;
    }

    /**
     * Method using AsyncHttpClient to make a request to the service.
     *
     * @param callback the callback when the method get the result from the service.
     * @throws InterruptedException
     */
    public void receiver(final Callback<JSONArray> callback) throws InterruptedException {
        double longitude = 0;
        double latitude = 0;

        gps = new GPSTracker(context, activity);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


        client.get("http://10.0.2.2:8080/GoodDealsws/webapi/offers?longitude=" + -0.369985 + "&latitude=" + 49.213250 +
                        "&prefDistance=" + prefDistance + "&category=" + category,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);

                        if (callback != null)
                            callback.onResponse(response);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                });


    }

    /**
     * Method using AsyncHttpClient to make a request to the service to get the offers proposed by the client
     * friends on Facebook
     *
     * @param friendslist client Facebook friends list
     * @param callback    the callback when the method get the result from the service.
     */

    public void friendsReceiver(String friendslist, final Callback<JSONArray> callback) {
        try {
            StringEntity entity = new StringEntity(friendslist);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


            client.post(context, "http://10.0.2.2:8080/GoodDealsws/webapi/offers/friends", entity, "application/json",
                    new JsonHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);

                            if (callback != null)
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

