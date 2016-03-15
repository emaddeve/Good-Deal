package com.emad.gooddeals.http;

import android.content.Context;
import android.os.AsyncTask;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by emad on 09/03/16.
 */
public class Sender  {


    // json object to be send to the server
    JSONObject parameters;
    // string to save the response code coming from the server
    String serverResponse;
    Context context;
    private static AsyncHttpClient client = new AsyncHttpClient();
    //constructor to initialize the paramerters


    public void send(JSONObject parameters){
        try {
            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth("emad","emad");
            client.post(context, "http://10.0.2.2:8080/GoodDealsws/webapi/offers/add", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                JSONObject json = new JSONObject(
                                        new String(responseBody));

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
