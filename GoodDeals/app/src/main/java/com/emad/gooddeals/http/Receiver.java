package com.emad.gooddeals.http;

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
    public JSONArray receiver() {

        final AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://10.0.2.2:8080/GoodDealsws/webapi/offers/", new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                jsonArray=response;



            }
        });
        return jsonArray;
    }
}

