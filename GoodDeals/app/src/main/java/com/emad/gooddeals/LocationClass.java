package com.emad.gooddeals;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by emad on 04/03/16.
 */
public class LocationClass {

    JSONObject jsonobj = new JSONObject();
    Bitmap m ;
public void ss() {

    try {
        jsonobj.put("name", "Aneh");
        jsonobj.put("ss",m);
    } catch (JSONException e) {
        e.printStackTrace();
    }

}

}
