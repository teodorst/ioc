package com.example.ioc.evshare.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class EventImagesAdapter extends BaseAdapter {
    private Context myContext;
    private List<Bitmap> myThumbnails;

    public EventImagesAdapter(Context c, List<Bitmap> thumbnails) {
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
        return null;
    }
}
