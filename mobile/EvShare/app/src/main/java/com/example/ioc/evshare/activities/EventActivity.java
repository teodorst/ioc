package com.example.ioc.evshare.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.model.Event;

public class EventActivity extends AppCompatActivity {
    private static final String TAG = "CreateUserActivity";

    private String eventId;
    private Event event;
    private GridView imagesGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // get event id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                eventId = null;
            }
            else {
                eventId = extras.getString("eventId");
            }
            Log.d(TAG, "onCreate: " + eventId);
        }

        // connect UI
        imagesGridView = (GridView) findViewById(R.id.event_images_grid_view);
        imagesGridView.setAdapter(new EventImagesAdapter(this));

        imagesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + i + " " + l);
            }
        });

    }
}
