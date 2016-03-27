package com.emad.gooddeals;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.emad.gooddeals.geolocation.Map;
import com.emad.gooddeals.http.Sender;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by stevo on 27/03/16.
 */
public class DetailOffer extends AppCompatActivity {
    TextView titreView;
    TextView categoryView;
    TextView magasinView;
    TextView dateView;
    ImageView imageView;
    TextView descrView;
    ImageButton carte_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar);
        toolbarTop.setTitle("Offer Details");
        setTitle("My Title");
        Intent intent = getIntent();
        String titre = intent.getStringExtra("titre");
        String description = intent.getStringExtra("description");
        String category = intent.getStringExtra("categorie");
        final String magasin = intent.getStringExtra("magasin");
        Log.v("dtaisl",magasin);
        final double longitude = intent.getDoubleExtra("longitude", 0);
        Log.v("hhhhhh",""+longitude);
        final double latitude = intent.getDoubleExtra("latitude",0);

        String date = intent.getStringExtra("date");
        Bitmap image = (Bitmap) intent.getParcelableExtra("image");
        imageView= (ImageView) findViewById(R.id.image_offer);
        categoryView= (TextView) findViewById(R.id.category_offer);
        magasinView= (TextView) findViewById(R.id.magasin_offer);
        dateView= (TextView) findViewById(R.id.date_offer);
        descrView= (TextView) findViewById(R.id.descr_offer);
        titreView= (TextView) findViewById(R.id.titre_offer);
        imageView.setImageBitmap(image);
        categoryView.setText(category);
        magasinView.setText(magasin);

        dateView.setText(date);
        descrView.setText(description);
        titreView.setText(titre);
        carte_button= (ImageButton) findViewById(R.id.carte_offre);
        carte_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DetailOffer.this,Map.class);
                intent1.putExtra("longitude",longitude);
                intent1.putExtra("latitude",latitude);
                intent1.putExtra("magasin",magasin);
                startActivity(intent1);
            }

        });


    }


}
