package com.charukochhar.cs478.application2project3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static com.charukochhar.cs478.application2project3.MainActivity.EXTRA_RES_ID;

/**
 * Created by Charu on 01-Nov-17.
 */

public class OpenImage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("Openimage","image displayed");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_image);
        Intent i1;
        // Get Intent Extra Value passing Landmark Image from Main Activity
        i1 = getIntent();
        // Set Image View to display the Image from main activity
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(i1.getIntExtra(EXTRA_RES_ID,0));
        setContentView(imageView);

            }
}
