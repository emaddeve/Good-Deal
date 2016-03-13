package com.emad.gooddeals.http;

import android.util.Log;

import com.emad.gooddeals.MainActivity;
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

private JSONArray jsonArray = new JSONArray();

    public   void receiver() throws InterruptedException {


        final AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://10.0.2.2:8080/GoodDealsws/webapi/offers/", new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                jsonArray = response;
                Log.v("response", response.toString());



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



    }
}

