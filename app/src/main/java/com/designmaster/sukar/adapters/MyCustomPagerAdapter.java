package com.designmaster.sukar.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.designmaster.sukar.R;
import com.designmaster.sukar.activities.ZoomtestActivity;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context c;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;
    public MyCustomPagerAdapter(Context c, ArrayList<String> imagePaths) {
        this._imagePaths = imagePaths;
        this.c = c;
    }
    @Override    public int getCount() {
        return this._imagePaths.size();
    }
    @Override    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }
    @Override    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.item, container,
                false);
        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imageViewMain);

       /* Picasso.with(c).load(_imagePaths.get(position))
                .into(
                        imgDisplay
                );*/
        Log.d("imgpath",_imagePaths.get(position));
        Glide.with(c).load(_imagePaths.get(position)).into(imgDisplay);
        (container).addView(viewLayout);

        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* if(position==1){*/
                // imageView.buildDrawingCache();
                // final Bitmap bitmap = imageView.getDrawingCache();
                Intent intent = new Intent(c, ZoomtestActivity.class);
                intent.putExtra("imageID", _imagePaths.get(position));
                Log.d("imageID",_imagePaths.get(position));
                c.startActivity(intent);

                // }
                // Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((LinearLayout) object);
    }
}