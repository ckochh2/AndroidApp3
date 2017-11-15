package com.charukochhar.cs478.application1project3;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LinkListFragment.ListSelectionListener {
    public static String[] mLandmarks;
    public static String[] mUrls;

    // To Check Application1Project3 and Application2Project3 have same actions define TOAST_INTENT
    private static final String TOAST_INTENT =
            "com.charukochhar.cs478.myReceiver.showToast";

    // Permission defined
    public static final String PROJECT_PERMISSION =
            "com.charukochhar.cs478.Project3Permission" ;

   private static final int MY_PERMISSIONS_REQUEST=1;

    // Create Fragment 2 of Application1Project3
    private final WebViewFragment webViewFragment = new WebViewFragment();
    private FragmentManager mFragmentManager;

    private FrameLayout mLandmarkLayout, mWebPageLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";

    // onConfiguration defined in case the orientation changes from Portrait to Landscape or viceversa
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set Layout both the Fragments in one screen in 1/3 and 2/3
            setLayout();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Set Layout only Fragment First Fragment if click show only second fragment
            setLayout();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        // Get the string arrays with the LANDMARKS AND THEIR WEBSITES
        mLandmarks = getResources().getStringArray(R.array.landmarks);
        mUrls = getResources().getStringArray(R.array.URLs);
        setContentView(R.layout.activity_main);

        // Get references to the ListFragment and to the WebPageFragment
        mLandmarkLayout = (FrameLayout) findViewById(R.id.listTitleFragment);
        mWebPageLayout = (FrameLayout) findViewById(R.id.webPageFragment);

        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.listTitleFragment, new LinkListFragment());

        fragmentTransaction.commit();

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                setLayout();
            }
        });

    }

    private void setLayout() {

        // Checks the orientation of the screen
        int orientation = getResources().getConfiguration().orientation;
        // if the orientation is Portrait
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (!webViewFragment.isAdded()) {
                mLandmarkLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                mWebPageLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            } else {
                mLandmarkLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));
                mWebPageLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 3f));

            }
        }
        else {                      // if the orientation is Landscape
            if (!webViewFragment.isAdded()) {

                mLandmarkLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                mWebPageLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            } else {
                // Make the LandmarkLayout take 1/3 of the layout's width
                mLandmarkLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
                // Make the WebPageLayout take 2/3's of the layout's width
                mWebPageLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
    }


    public void onListSelection(int index) {

        // If the webViewFragment has not been added, add it now
        if (!webViewFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the webPageFragment to the layout
            fragmentTransaction.add(R.id.webPageFragment,webViewFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }
        if (webViewFragment.getShownIndex() != index) {

            // Tell the webPageFragment to show the webpage at position index
            webViewFragment.showWebPageAtIndex(index);

        }

    }

    // Defines the overflow area

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    // Action defined for overflow area
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.exitApp1:
                finishAndRemoveTask();
                return true;
            case R.id.launchApp2:
                if (ContextCompat.checkSelfPermission(MainActivity.this, PROJECT_PERMISSION)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{PROJECT_PERMISSION}, MY_PERMISSIONS_REQUEST);
                    //return true;
                } else {
                    Intent intent = new Intent();
                    intent.setAction(TOAST_INTENT);
                    sendBroadcast(intent);
                }

            default: return super.onOptionsItemSelected(menuItem);
        }
    }

    // Defines the default onRequestPermissionsResult method to determine action to be taken when Permissions are ALLOW or DENY
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults){
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted
                    // Launch App A2 i.e. Application2Project3.
                    Log.i("Main Activity","Allow Permission");
                    Intent intent=new Intent();
                    intent.setAction(TOAST_INTENT);
                    sendBroadcast(intent);
                } else {

                    // permission denied, Do nothing Return back to Application1Project3
                    break;
                }
                return;
            }
        }
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }
}
