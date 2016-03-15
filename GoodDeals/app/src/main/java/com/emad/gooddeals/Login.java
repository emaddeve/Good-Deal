package com.emad.gooddeals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.serialization.SoapObject;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class Login extends AppCompatActivity {
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        // Permet d'accéder à la liste d'amis qui utilisent l'application
        loginButton.setReadPermissions ( "user_friends" );
        //permet de savoir si un user est deja connecte
        //AccessToken.getCurrentAccessToken();
        getLoginDetails(loginButton);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Register a callback function with LoginButton to respond to the login result.
     */

    protected void getLoginDetails(LoginButton login_button) {
        // Callback registration
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            /**
             * lorsque la connexion est reussi le login manager
             * definit le access token et le profil du user connecte
             * Ces infos sont enregistres dans les preference partages par le facebook sdk  pendant SDK initialisation
             *
             * */
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                        loginResult.getAccessToken(),
                        "/me/friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                               // Intent intent = new Intent(Login.this,FriendsList.class);
                               // try {
                                   // JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                   // intent.putExtra("jsondata", rawName.toString());
                                    Toast.makeText(Login.this, "taille " + response.toString(),
                                            Toast.LENGTH_LONG).show();
                                   // startActivity(intent);
                              //  } catch (JSONException e) {
                                  //  e.printStackTrace();
                              //  }
                            }
                        }).executeAsync();

            }

            @Override
            public void onCancel() {
                // code for cancellation
            }

            @Override
            public void onError(FacebookException exception) {
                //  code to handle error
            }
        });
    }

    /**
     * Initialize the facebook sdk.
     * And then callback manager will handle the login responses.
     */
    protected void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data", data.toString());
    }

}
