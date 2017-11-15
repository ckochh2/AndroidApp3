package com.charukochhar.cs478.application2project3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

// Define the MAin Activity of Application2Project3 showing images in gridView
public class MainActivity extends AppCompatActivity {

    protected static final String EXTRA_RES_ID="POS";

    // Initialize the Grid View
    GridView gridView;
    int[] landmarkImage = {R.drawable.abrahamlincoln, R.drawable.lighthouse, R.drawable.lincolnparkzoo,
            R.drawable.scienceandindustrymuseum, R.drawable.villagetheatre,
            R.drawable.wrigleyfield};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the adapter
        ImageAdapter adapter = new ImageAdapter(MainActivity.this, landmarkImage);
        gridView = (GridView) findViewById(R.id.gridViewid);
        gridView.setAdapter(adapter);

        // OnClick Code to Open Image in a new window
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                        {
                                            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                                            {
                                                Log.i("MainActivity","Launch intent");
                                                Intent newintent = new Intent(getApplicationContext(),OpenImage.class);
                                                newintent.putExtra(EXTRA_RES_ID,landmarkImage[position]);
                                                startActivity(newintent);
                                            }
                                        }
        );

    }

    public static class MyReceiver extends BroadcastReceiver {

        // Set up the onReceive method to make response to the Broadcast sent by Sender and launch Application2Project3
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("MainActivity",":entered onReceive()");
            Intent i=new Intent();
            i.setClassName("com.charukochhar.cs478.application2project3","com.charukochhar.cs478.application2project3.MainActivity");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
}

