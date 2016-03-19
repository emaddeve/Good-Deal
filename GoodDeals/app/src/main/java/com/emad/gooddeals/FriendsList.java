package com.emad.gooddeals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by stevo on 14/03/16.
 */
public class FriendsList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
String[]tab=new String[]{"stevo","jean","ivan"};
        Intent intent = getIntent();
        // receiving data from  MainActivity.java
        String jsondata = intent.getStringExtra("jsondata");
        FacebookSdk.sdkInitialize(getApplicationContext());
        JSONArray friendslist;
        ArrayList<String> friends = new ArrayList<String>();
        try {
            friendslist = new JSONArray(jsondata);
            for (int l=0; l < friendslist.length(); l++) {
                friends.add(friendslist.getJSONObject(l).getString("first_name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "profil " + Profile.getCurrentProfile(),
                Toast.LENGTH_LONG).show();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, friends);
        ListView listView = (ListView) findViewById(R.id.listView_friend);
        listView.setAdapter(adapter);

    }

}
