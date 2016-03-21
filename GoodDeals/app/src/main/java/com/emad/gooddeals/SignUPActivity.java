package com.emad.gooddeals;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

/**
 * Created by stevo on 15/03/16.
 */
public class SignUPActivity extends Activity {
    private static final String TAG = "SignUPActivity";
    private CallbackManager callbackManager;
    EditText editTextUserName, editTextPassword, editTextConfirmPassword, editFirstName, editLastName;
    Button btnCreateAccount, btnCreateAccountWithFacebook;
    TextView linkLogin;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
       // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        setContentView(R.layout.activity_signup);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccountWithFacebook = (Button) findViewById(R.id.buttonCreateAccountwithfacebook);
        linkLogin = (TextView) findViewById(R.id.link_login);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
        btnCreateAccountWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProfilDetail();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        btnCreateAccount.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUPActivity.this,
                R.style.com_facebook_auth_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String email = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String passwordConfirm = editTextConfirmPassword.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        btnCreateAccount.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        Toast.makeText(this, "Compte creer.Vous pouvez vous connecter !", Toast.LENGTH_LONG).show();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnCreateAccount.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String email = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String passwordConfirm = editTextConfirmPassword.getText().toString();

        if (firstName.isEmpty() || firstName.length() < 3) {
            editFirstName.setError("at least 3 characters");
            valid = false;
        } else {
            editFirstName.setError(null);
        }
        if (lastName.isEmpty() || lastName.length() < 3) {
            editLastName.setError("at least 3 characters");
            valid = false;
        } else {
            editLastName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextUserName.setError("enter a valid email address");
            valid = false;
        } else {
            editTextUserName.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editTextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }
        if (email.equals("") || password.equals("")
                || passwordConfirm.equals("") || firstName.equals("") || lastName.equals("")) {

            Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs",
                    Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (!password.equals(passwordConfirm)) {
            Toast.makeText(getApplicationContext(),
                    "Password does not match", Toast.LENGTH_LONG)
                    .show();
            valid = false;
        } else {

            //insertion des donnees en BDD affichage dun toast de success et redirection vers laecran de login
            /** Toast.makeText(getApplicationContext(),
             "Account Successfully Created ", Toast.LENGTH_LONG)
             .show();
             Intent i = new Intent(SignUPActivity.this,
             Login.class);
             startActivity(i);
             finish();*/

        }

        return valid;
    }

    /**
     * Register a callback function with LoginButton to respond to the login result.
     */
    protected void getProfilDetail() {
        // Callback registration
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends","email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
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
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "email,first_name,last_name");
                        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                                loginResult.getAccessToken(),
                                "/me",
                                parameters,
                                HttpMethod.GET,
                                new GraphRequest.Callback() {
                                    public void onCompleted(GraphResponse response) {
                                        //Intent intent = new Intent(Login.this, FriendsList.class);
                                        try {
                                        //  intent.putExtra("jsondata", rawName.toString());
                                        //  startActivity(intent);
                                            editTextUserName.setText( response.getJSONObject().getString("email"));
                                            editFirstName.setText( response.getJSONObject().getString("first_name"));
                                            editLastName.setText( response.getJSONObject().getString("last_name"));;
                                    } catch (JSONException e) {
                                     e.printStackTrace();
                                    }
                                    }
                                }).executeAsync();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data", data.toString());
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

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}