package com.charukochhar.cs478.application2project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Charu on 01-Nov-17.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    public final int[] landmarkimage;

    public ImageAdapter(Context c,int[] landmarkimage)
    {
        mContext =c;
        this.landmarkimage=landmarkimage;
    }

    public int getCount()
    {
        return landmarkimage.length;
    }

    public Object getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent ) {

        View grid;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            grid = new View(mContext);
            grid=inflater.inflate(R.layout.singleitem,null);
            ImageView imageView = (ImageView)grid.findViewById(R.id.landmark_image);
            // Reduce the image size
            grid.setLayoutParams(new GridView.LayoutParams(400,400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(landmarkimage[position]);
            grid.setPadding(5, 5, 5, 5);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }


}
