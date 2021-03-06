package com.emad.gooddeals.registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import com.emad.gooddeals.http.Callback;
import com.emad.gooddeals.MainActivity;
import com.emad.gooddeals.R;
import com.emad.gooddeals.http.Sender;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;



public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;
    private CallbackManager callbackManager;
    private EditText editUsername;
    private EditText editPassword;
    private Button submit;
    private TextView register;
    private Sender sender;
     String username;
     String password;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        sp= this.getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sp.edit();
        sender = new Sender();
        setContentView(R.layout.activity_login);
        editUsername = (EditText) findViewById(R.id.input_username);
        editPassword = (EditText) findViewById(R.id.input_password);
        submit = (Button) findViewById(R.id.submit_button);
        register = (TextView) findViewById(R.id.reg);
/*
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProfilDetail();
            }
        });

*/

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

         progressDialog = new ProgressDialog(Login.this,
                R.style.com_facebook_auth_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

      username = editUsername.getText().toString();
        password = editPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        sender.login(username, password, new Callback<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.v("auth", "" + response);
                if (response.equalsIgnoreCase("true")) {
                    onLoginSuccess();
                } else {
                    Log.v("loginfaild", "true");
                    onLoginFailed();
                }
            }


        });
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

        Log.v("sharedpref",""+username+": "+password);
        editor.putString("email", username);
        editor.putString("pass",password);
        editor.commit();

        submit.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        Log.v("loginfaild2", "true");
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

/*

    protected void getProfilDetail() {
        // Callback registration
        intent= new Intent(this, SignUpFacebook.class);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "email,first_name,last_name");
                        GraphRequestBatch batch = new GraphRequestBatch(
                                new GraphRequest(
                                        loginResult.getAccessToken(),
                                        "/me",
                                        parameters,
                                        HttpMethod.GET,
                                        new GraphRequest.Callback() {
                                            public void onCompleted(GraphResponse response) {
                                                Log.i("objetfri",
                                                        response.toString());
                                                jsontoken = response.getJSONObject();

                                                 infoUser = response.getJSONObject().toString();
                                                intent.putExtra("jsondatauser", infoUser);
                                            }
                                        }),
                                new GraphRequest(
                                        loginResult.getAccessToken(),
                                        "/me/friends",
                                        parameters,
                                        HttpMethod.GET,
                                        new GraphRequest.Callback() {
                                            public void onCompleted(GraphResponse response) {

                                                try {
                                                    rawName = response.getJSONObject().getJSONArray("data");
                                                    intent.putExtra("jsondatafriend", rawName.toString());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                Log.v("dataidfac", response.toString());

                                            }
                                        })
                        );
                        batch.addCallback(new GraphRequestBatch.Callback() {
                            @Override
                            public void onBatchCompleted(GraphRequestBatch graphRequests) {
                                String username = editUsername.getText().toString();
                                String password = editPassword.getText().toString();
                                try {
                                    token = jsontoken.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.v("tttt",token);
                                sender.loginFacebook(token, new Callback<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        Log.v("auth", "" + response);
                                        if (response!=null) {
                                            ArrayList<String> friends = new ArrayList<>();
                                            try {

                                                for (int i = 0; i < rawName.length(); i++) {

                                                    JSONObject json = null;
                                                    json = rawName.getJSONObject(i);
                                                    friends.add(json.getString("id"));



                                            }

                                                String email = jsontoken.getString("email");
                                                String pass = response;
                                                Log.v("blabla",email+" :"+pass+": "+friends.toString());
                                                setpreference(email,pass,friends.toString());
                                            }catch(JSONException e){
                                                e.printStackTrace();
                                            }

                                        } else {
                                            Log.v("loginfaild", "true");
                                            onLoginFailed();
                                        }
                                    }


                                });
                                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);


                                startActivity(intent1);
                            }
                        });
                        batch.executeAsync();

                        LoginManager.getInstance().logOut();
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



*/


    public void setpreference(String email,String password,String friends){
        SharedPreferences sp= this.getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Log.v("sharedpref", "" + email + ": " + password);
        editor.putString("email", email);
        editor.putString("pass", password);
        editor.putString("friendslist", friends);
        editor.commit();
    }


}
