package com.emad.gooddeals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emad.gooddeals.registration.SignUPActivity;

/**
 * Created by stevo on 15/03/16.
 */
public class HomeScreenActivity extends Activity {
    Button btnSignUp;
    TextView link;
    public static final String MyPREFERENCES = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp= this.getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        String email = sp.getString("email", "Empty");

        String pass = sp.getString("pass", "Empty");
        if(!email.equalsIgnoreCase("Empty") && !pass.equalsIgnoreCase("Empty")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);
        link= (TextView) findViewById(R.id.link);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intentSignIN = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intentSignIN);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
