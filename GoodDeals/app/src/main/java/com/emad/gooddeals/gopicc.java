package com.emad.gooddeals;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class gopicc extends Fragment {


    /**
     * Standard activity result: operation canceled.
     */
    public static final int RESULT_CANCELED = 0;
    /**
     * Standard activity result: operation succeeded.
     */
    public static final int RESULT_OK = -1;
    ImageToJson imageToJson = new ImageToJson();

    Bitmap photo;

    private ImageView imageView;
    private ImageView imageView2;
    Bitmap bitmappic;
    JSONObject jsonObject;
    EditText e;
    String encodedImage;
}
/*
    private static final int CAMERA_REQUEST = 1888;
    ImageView btnimage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sendofferfrag, container, false);

        btnimage = (ImageView) view.findViewById(R.id.button);
        btnimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_REQUEST) {
            GPSTracker gps = new GPSTracker(getContext());

            if (resultCode == RESULT_OK) {
               // previewCapturedImage();
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Toast.makeText(getContext(), "Your Location is - \nLat: "   latitude   "\nLong: "   longitude, Toast.LENGTH_LONG).show();
                    photo = (Bitmap) data.getExtras().get("data");
                    Intent i = new Intent(getActivity(),TakePhoto.class);
                    i.putExtra("image",photo);
                    i.putExtra("latitude",latitude);
                    i.putExtra("longitude", longitude);
                    startActivity(i);

                    // \n is for new line

                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.

                }

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getContext(),
                        "Cancelled", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getContext(),
                        "Error!", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }
}
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAMERA_REQUEST) {
            photo = (Bitmap) data.getExtras().get("data");
            Intent i = new Intent(getActivity(),gopicc.class);
            i.putExtra("image",photo);
            startActivity(i);
            // imageView.setImageBitmap(photo);

            encodedImage= imageToJson.getStringFromBitmap(photo);
          //  e.setText(encodedImage);
          //  try {
            //    jsonObject = new JSONObject("{\"image\":\""   encodedImage  "\"}");
             //   String jsonString = jsonObject.getString("image");
             //   Bitmap m = imageToJson.getBitmapFromString(jsonString);
             //   imageView.setImageBitmap(m);
          //  }catch(JSONException e){}
        }
    }


}
*/
