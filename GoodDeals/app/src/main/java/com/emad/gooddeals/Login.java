package com.emad.gooddeals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private EditText editUsername;
    private EditText editPassword;
    private Button submit;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        editUsername = (EditText) findViewById(R.id.input_username);
        editPassword = (EditText) findViewById(R.id.input_password);
        submit = (Button) findViewById(R.id.submit_button);
        register = (TextView) findViewById(R.id.reg);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        // Permet d'accéder à la liste d'amis qui utilisent l'application
        loginButton.setReadPermissions("user_friends");
        //permet de savoir si un user est deja connecte
        //AccessToken.getCurrentAccessToken();
        getLoginDetails(loginButton);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        submit.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.com_facebook_auth_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    /**
     *
     *Methode dans laquelle on va definir les instructions a faire pendant que le progress dialog est entrain de charger.
     * */
    public void onLoginSuccess() {
        submit.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        submit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            editUsername.setError("enter a valid email address");
            valid = false;
        } else {
            editUsername.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editPassword.setError(null);
        }

        return valid;
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
                                Intent intent = new Intent(Login.this, FriendsList.class);
                                try {
                                    Log.v("friends",response.toString());
                                    JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                    intent.putExtra("jsondata", rawName.toString());
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //Log.e("data", data.toString());
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
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
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }


}
