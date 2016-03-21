package com.emad.gooddeals;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by stevo on 15/03/16.
 */
public class HomeScreenActivity extends Activity {
    Button btnSignUp;
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
