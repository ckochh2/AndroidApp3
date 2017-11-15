package com.charukochhar.cs478.application1project3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Charu on 01-Nov-17.
 */
//  Log messages are applied for Debugging
// Create Fragment 2 for Application1Project3 used WebView
public class WebViewFragment extends Fragment {
    private static final String TAG = "webpageFragment";

    private WebView mlandmarksView=null;
    private int mCurrIdx = -1;
    private int mUrlArrLen;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Webpage at position newIndex
    void showWebPageAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mUrlArrLen)
            return;
        mCurrIdx = newIndex;
    //Load the WebPage URL
        mlandmarksView.loadUrl(MainActivity.mUrls[mCurrIdx]);

    }

    @Override
    public void onAttach(Activity activity) {
      Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout defined in fragment2.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.fragment2,container, false);
    }

    // Set up some information about the WebPAge View
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        mlandmarksView = (WebView) getActivity().findViewById(R.id.showWebPage);
        mUrlArrLen = MainActivity.mUrls.length;
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

}
