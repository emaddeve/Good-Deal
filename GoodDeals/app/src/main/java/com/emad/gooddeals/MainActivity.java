package com.emad.gooddeals;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.emad.gooddeals.http.Receiver;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import data.stevo.SQlite.Offres;

import com.facebook.FacebookSdk;

/**
 * Created by emad on 23/02/16.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,SwipeRefreshLayout.OnRefreshListener {

    GPSTracker gps;
    JSONObject jsonObject = new JSONObject();
    String encodedImage;
    Bitmap image;
    Bitmap image2;
    String imageString;
    double longitude;
    double lit;
    String name;
    String desc;
    Point p1;
    String s = "name of offer";
    String s2 = "descritpi about ;thies";
    private SwipeRefreshLayout swipeRefreshLayout;
    private Bitmap photo;
    private Context context;
    private ImageToJson imageToJson;
    private JSONArray jsonArray;
    private Receiver receiver;
    public static final String MyPREFERENCES = "MyPrefs";

    private static final int CAMERA_REQUEST = 1888;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public ListView listView;
    private SwipeRefreshLayout swipeContainer;
    CustomAdapter adapter;
    ArrayList<Offres> myList;
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = new ArrayList<Offres>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listviewperso);
        adapter = new CustomAdapter(this,myList,this);
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetch();
                                    }
                                }
        );


        sp = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sp.edit();

        jsonArray = new JSONArray();
        imageToJson = new ImageToJson();
        /**
         * initialisation des valeurs par defaut de nos preferences lors de la premiere arriver sur cette activite
         *
         * */
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        //test preference
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        int seekbarValue = SP.getInt("SEEKBAR_VALUE", 50);
        String categorytypeValue = SP.getString("categorytype", "toutes");
        Boolean activeVue = SP.getBoolean("offre_ami", false);
        Toast.makeText(this, "la categorie est " + categorytypeValue + " et la distance est de " + seekbarValue
                        + "display offer to my friend " + activeVue,
                Toast.LENGTH_LONG).show();

        jsonArray = new JSONArray();



        int prefDistance = SP.getInt("SEEKBAR_VALUE", 0);
        String category = SP.getString("categorytype", "all");

        receiver = new Receiver(category, prefDistance, this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_REQUEST) {


            if (resultCode == RESULT_OK) {
                // previewCapturedImage();

                photo = (Bitmap) data.getExtras().get("data");
                Intent i = new Intent(this, TakePhoto.class);
                i.putExtra("image", photo);
                startActivity(i);

            }
        } else if (resultCode == RESULT_CANCELED) {
            // user cancelled Image capture
            Toast.makeText(this,
                    "Cancelled", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // failed to capture image
            Toast.makeText(this,
                    "Error!", Toast.LENGTH_SHORT)
                    .show();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /***/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_manage) {
            Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(settings);
        }
        if (id == R.id.send_offer) {
            //Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
            //startActivity(settings);
        }
        if (id == R.id.deconnexion) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Log out")
                    .setMessage("Are you sure you want to Log out ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.emad.gooddeals/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.emad.gooddeals/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }


    @Override
    public void onResume() {
        super.onResume();
        // Logs' install 'and' activate app App Events
        AppEventsLogger.activateApp(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }


    @Override
    public void onRefresh() {
        fetch();
    }


    private void fetch() {
        swipeRefreshLayout.setRefreshing(true);

        try {
            receiver.receiver(new Callback<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    myList.clear();

                    try {
                        for (int i = 0; i < jsonArray.length(); i++) {


                            JSONObject jsonObject = jsonArray.getJSONObject(i);


                            myList.add(new Offres(jsonObject));

                        }

                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}
