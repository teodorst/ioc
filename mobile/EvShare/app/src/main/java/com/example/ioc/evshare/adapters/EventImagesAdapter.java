package com.example.ioc.evshare.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ioc.evshare.model.Photo;

import java.util.List;

public class EventImagesAdapter extends BaseAdapter {
    private Context myContext;
    private List<Photo> myThumbnails;

    public EventImagesAdapter(Context c, List<Photo> thumbnails) {
        myContext = c;
        myThumbnails = thumbnails;
    }


    public int getCount() {
        return myThumbnails.size();
    }

    public Object getItem(int position) {
        return myThumbnails.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(myContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(myThumbnails.get(position).getImage());
        return imageView;
    }

}
