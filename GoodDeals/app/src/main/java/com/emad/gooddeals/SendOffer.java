package com.emad.gooddeals;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * this class dealing with the http protocol to send the offer representing in jsonObject to the server
 * which will be running the background by extending AsyncTask
 * and reseiving an response code
 *
 *
 * Created by emad on 04/03/16.
 */
public class SendOffer extends AsyncTask<Void, Void, Void> {
    // json object to be send to the server
    JSONObject parameters;
    // string to save the response code coming from the server
    String serverResponse;

    //constructor to initialize the paramerters
    public SendOffer(JSONObject parameters) {
        this.parameters = parameters;
    }


    @Override
    protected Void doInBackground(Void... params) {
        StringBuffer response = null;
        try {
            URL url = new URL("http://localhost:8888/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(parameters.toString());
            System.out.println("params.tostring"  + parameters.toString());

            writer.close();
            out.close();


            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " +  url);
            System.out.println("Response Code : " +  responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response in universal: "+   response.toString());
        } catch (Exception exception) {
            System.out.println("Exception: "  + exception);


        }
        return null;
    }
}