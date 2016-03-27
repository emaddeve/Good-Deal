package com.emad.gooddeals.http;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Class for making requests to the service to send data
 * <p/>
 * Created by emad on 09/03/16.
 */
public class Sender {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private Context context;
    //constructor to initialize the paramerters

    /**
     * Method for sending a get request to the servie to get list of offers
     *
     * @param parameters parameters to filter the offer list
     * @param email      the email address of the client
     * @param password   password of the client
     */
    public void send(JSONObject parameters, String email, String password) {
        try {
            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth(email, password);
            client.post(context, "http://10.0.2.2:8080/GoodDealsws/webapi/offers/add", entity, "application/json",
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

    /**
     * Method to create a new account
     *
     * @param parameters the new account information
     * @param callback   the callback when the method get the result from the service.
     */
    public void signup(final JSONObject parameters, final Callback<Integer> callback) {


        Log.v("clientjson", parameters.toString());


        try {
            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            client.post(context, "http://10.0.2.2:8080/GoodDealsws/webapi/clients/add", entity, "application/json",
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

    /**
     * Method for creating a new account by Facebook
     * @param parameters the new account information
     * @param callback the callback when the method get the result from the service.
     */
    public void signupfacebook(JSONObject parameters, final Callback<Integer> callback) {

        try {

            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.post(context, "http://10.0.2.2:8080/GoodDealsws/webapi/clients/add", entity, "application/json",
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

    /**
     * Log in Method
     * @param name name of the user
     * @param pass password of the user
     * @param callback the callback when the method get the result from the service.
     */
    public void login(String name, String pass, final Callback<String> callback) {

        try {
            StringEntity entity = new StringEntity("null");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth(name, pass);

            client.get(context, "http://10.0.2.2:8080/GoodDealsws/webapi/clients/verify", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                            String respnse = new String(responseBody);

                            if (callback != null) {
                                callback.onResponse(respnse);

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

    /**
     * Log in Method by Facebook account
     * @param token
     * @param callback
     */

    public void loginFacebook(String token, final Callback<String> callback) {
        try {
            StringEntity entity = new StringEntity(token);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


            client.get(context, "http://10.0.2.2:8080/GoodDealsws/webapi/clients/loginInfo", entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                            String respnse = new String(responseBody);

                            if (callback != null) {
                                callback.onResponse(respnse);

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
