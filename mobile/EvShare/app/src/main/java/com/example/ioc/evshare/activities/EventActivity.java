package com.example.ioc.evshare.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.adapters.EventImagesAdapter;
import com.example.ioc.evshare.model.Event;
import com.example.ioc.evshare.model.Photo;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetThumbnailAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetThumbnailActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetEventActionMessage;
import com.example.ioc.evshare.network.api.EventService.GetEventResponse;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    private static final String TAG = "CreateUserActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Long eventId;
    private GridView imagesGridView;
    private List<Photo> eventPhotos;
    private Event currentEvent;
    private FloatingActionButton takePhotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // get event id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                eventId = null;
            }
            else {
                eventId = (Long) extras.get("eventId");
            }
            Log.d(TAG, "onCreate: " + eventId);
        }
        else {
            eventId = savedInstanceState.getLong("eventId");
        }

        eventPhotos = Collections.synchronizedList(new ArrayList<Photo>());

        // connect UI
        takePhotoButton = (FloatingActionButton) findViewById(R.id.take_photo_button);
        imagesGridView = (GridView) findViewById(R.id.event_images_grid_view);


        imagesGridView.setAdapter(new EventImagesAdapter(this, eventPhotos));
        takePhotoButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        takePhotoAction();
                    }
                }
        );

        imagesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + i + " " + l);
            }
        });


        // start chain of getting data
        getEvent();

    }


    private void takePhotoAction() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        if (eventId != null) {
            outState.putLong("eventId", eventId);
        }

    }

    private void getEvent() {
        GetEventActionMessage message = new GetEventActionMessage();
        Log.d(TAG, "getEvent: " + eventId);
        message.setId(eventId);
        BusProvider.bus().post(new GetEventAction.OnLoadingStart(message));
    }


    @Subscribe
    public void onLoadingSuccesful(GetEventAction.OnLoadedSuccess response) {
        Log.d(TAG, "GetEventAction.OnLoadingSuccessful: " + response.getResponse().getId());
        Log.d(TAG, "GetEventAction.OnLoadingSuccessful: " + response.getResponse().getPhotosIds());
        currentEvent = convertGetEventResponseToEvent(response.getResponse());
        getPhotos();
    }

    @Subscribe
    public void onLoadingFailed(GetEventAction.OnLoadingError response) {
        Log.d(TAG, "GetEventAction.OnLoadingError: " + response.getCode());
    }


    private void getPhotos() {
        if (currentEvent.getPhotosIds() != null) {
            for (Long photoId : currentEvent.getPhotosIds()) {
                GetThumbnailActionMessage downloadMessage = new GetThumbnailActionMessage();
                downloadMessage.setEventId(eventId);
                downloadMessage.setPhotoId(photoId);
                BusProvider.bus().post(new GetThumbnailAction.OnLoadingStart(downloadMessage));
            }
        }
    }

    @Subscribe
    public void onLoadingSuccesful(GetThumbnailAction.OnLoadedSuccess response) {
        Log.d(TAG, "onLoadingSucessful: " + response.getResponse().getImageId());
        Log.d(TAG, "onLoadingSuccessful: " + response.getResponse().getImage().toString());
        Photo newPhoto = new Photo();
        newPhoto.setId(response.getResponse().getImageId());
        newPhoto.setImage(BitmapFactory.decodeByteArray(response.getResponse().getImage() , 0, response.getResponse().getImage().length));
        eventPhotos.add(newPhoto);
        ((BaseAdapter) imagesGridView.getAdapter()).notifyDataSetChanged();
    }

    @Subscribe
    public void onLoadingFailed(GetThumbnailAction.OnLoadingError response) {
        Log.d(TAG, "GetThumbnailAction.OnLoadingError: " + response.getCode());
    }

    private Event convertGetEventResponseToEvent(GetEventResponse response) {
        return new Event(response.getId(), response.getName(), response.getLocation(), response.getDate(), response.getOwnerEmail(), response.getPhotosIds(), response.getPhotosCount());
    }

    // result photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.d(TAG, "onActivityResult: GOT IMAGE" + imageBitmap.getByteCount());
            
        }
    }
}
