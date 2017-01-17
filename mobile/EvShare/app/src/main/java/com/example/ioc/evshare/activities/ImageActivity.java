package com.example.ioc.evshare.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.model.Photo;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetImageAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetThumbnailAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetImageActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetThumbnailActionMessage;
import com.squareup.otto.Subscribe;

public class ImageActivity extends AppCompatActivity {
    private static final String TAG = "ImageActivity";

    Long eventId;

    Long photoId;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // get event id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                eventId = null;
            }
            else {
                eventId = (Long) extras.get("eventId");
                photoId = (Long) extras.get("photoId");
            }
        }
        else {
            eventId = savedInstanceState.getLong("eventId");
        }

        imageView = (ImageView) findViewById(R.id.event_image_view);

        Log.d(TAG, "onCreate: " + eventId + " " + photoId);
        download_photo();
    }


    private void download_photo() {
        if (eventId != null && photoId != null) {
            GetImageActionMessage downloadMessage = new GetImageActionMessage();
            downloadMessage.setEventId(eventId);
            downloadMessage.setPhotoId(photoId);
            BusProvider.bus().post(new GetImageAction.OnLoadingStart(downloadMessage));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.bus().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        BusProvider.bus().unregister(this);
    }

    @Subscribe
    public void onLoadingSuccesful(GetImageAction.OnLoadedSuccess response) {
        Photo newPhoto = new Photo();
        Bitmap photo = BitmapFactory.decodeByteArray(response.getResponse().getImage() , 0, response.getResponse().getImage().length);
        imageView.setImageBitmap(photo);
        Log.d(TAG, "onLoadingSuccesful: event photots");
    }

    @Subscribe
    public void onLoadingFailed(GetImageAction.OnLoadingError response) {
        Log.d(TAG, "GetImageAction.OnLoadingError: " + response.getCode());
    }



}
