package com.emad.gooddeals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stevo on 21/03/16.
 */
public class SignUpFacebook extends AppCompatActivity {
    Button createAccount;
    EditText editTextPassword2, editTextConfirmPassword2;
    TextView result;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_facebook);

        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);
        editTextConfirmPassword2 = (EditText) findViewById(R.id.editTextConfirmPassword2);
        createAccount = (Button) findViewById(R.id.createAccount);
        result= (TextView) findViewById(R.id.textViewResult);
        result.setVisibility(View.GONE);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }

        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void signup() {
       /* Log.d(TAG, "Signupfacebook"); */

        if (!validate()) {
            onSignupFailed();
            return;
        }
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("jsondata");
        FacebookSdk.sdkInitialize(getApplicationContext());
        JSONObject infoUser;
        createAccount.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpFacebook.this,
                R.style.com_facebook_auth_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        try {
            infoUser = new JSONObject(jsondata);
            String firstName = infoUser.getString("first_name");
            String lastName = infoUser.getString("last_name");
            String email = infoUser.getString("email");
            String password = editTextPassword2.getText().toString();
            String passwordConfirm = editTextConfirmPassword2.getText().toString();
            //Toast.makeText(this,"info :"+infoUser.getString("email")+" "+infoUser.getString("first_name"),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // TODO: Implement your own signup logic here.

        new Handler().postDelayed(
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
        createAccount.setEnabled(true);
        setResult(RESULT_OK, null);
        // Intent intent = new Intent(getApplicationContext(), Login.class);
        //startActivity(intent);
        result.setVisibility(View.VISIBLE);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });
        Toast.makeText(this, " Account successfully created.!", Toast.LENGTH_LONG).show();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Register failed", Toast.LENGTH_LONG).show();

        createAccount.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String password = editTextPassword2.getText().toString();
        String passwordConfirm = editTextConfirmPassword2.getText().toString();
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editTextPassword2.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword2.setError(null);
        }
        if (password.equals("")
                || passwordConfirm.equals("")) {

            Toast.makeText(getApplicationContext(), "Veuillez remplir les champs",
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SignUpFacebook Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.emad.gooddeals/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SignUpFacebook Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.emad.gooddeals/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

