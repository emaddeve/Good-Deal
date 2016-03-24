package com.emad.gooddeals.http;

import android.content.Context;
import android.util.Log;

import com.emad.gooddeals.Callback;
import com.emad.gooddeals.registration.Login;
import com.emad.gooddeals.registration.SignUPActivity;
import com.emad.gooddeals.registration.SignUpFacebook;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

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
    boolean b = false;

    // json object to be send to the server
    JSONObject parameters;
    // string to save the response code coming from the server
    String serverResponse;
    Context context;
    private static AsyncHttpClient client = new AsyncHttpClient();
    //constructor to initialize the paramerters


    public void send(JSONObject parameters,String email,String password){
        try {
            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth(email,password);
            client.post(context, "http://10.241.68.68:8080/GoodDealsws/webapi/offers/add", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    public void signup(final JSONObject parameters,final Callback<Integer> callback){

        final SignUPActivity signUPActivity = new SignUPActivity();

            Log.v("clientjson", parameters.toString());


                            try {
                            StringEntity entity = new StringEntity(parameters.toString());
                            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            client.post(context, "http://10.241.68.68:8080/GoodDealsws/webapi/clients/add", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            if (callback != null) {
                                callback.onResponse(statusCode);
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

    public void signupfacebook(JSONObject parameters,final Callback<Integer> callback){
        final SignUpFacebook signUpFacebook = new SignUpFacebook();
        try {

            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth("emad@gmail.com", "emad");
            client.post(context, "http://10.241.68.68:8080/GoodDealsws/webapi/clients/add", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            if (callback != null) {
                                callback.onResponse(statusCode);
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

    public void login(String name, String pass, final Callback<Integer> callback){
        final Login login = new Login();
        try {
            StringEntity entity = new StringEntity("null");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth(name,pass);

            client.get(context, "http://10.241.68.68:8080/GoodDealsws/webapi/clients/verify", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                            if (callback != null) {
                                callback.onResponse(statusCode);
                            }


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            if (callback != null) {
                                callback.onResponse(statusCode);
                            }
                        }
                    });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void loginFacebook(String token, final Callback<JSONObject> callback){
        try {
            StringEntity entity = new StringEntity(token);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


            client.get(context, "http://10.241.68.68:8080/GoodDealsws/webapi/clients/loginInfo", entity, "application/json",
                    new JsonHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);


                            if (callback != null) {
                                callback.onResponse(response);
                            }
                        }


                    });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}
