package com.example.ioc.evshare.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.model.Photo;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetThumbnailAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.InviteEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.InviteEventActionMessage;
import com.example.ioc.evshare.network.api.EventService.InviteEventRequest;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Collections;

public class InviteEventActivity extends AppCompatActivity {
    private static final String TAG = "INVITE_USER_ACTIVITY";

    private FloatingActionButton inviteButton;
    private EditText emailAddress;
    private Long eventId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        emailAddress = (EditText) findViewById(R.id.user_email_input);

        // get event id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                eventId = null;
            }
            else {
                eventId = (Long) extras.get("eventId");
            }
        }
        else {
            eventId = savedInstanceState.getLong("eventId");
        }

        Log.d(TAG, "onCreate: eventId  " + eventId);

        inviteButton = (FloatingActionButton) findViewById(R.id.fab);
        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inviteUser();
            }
        });
    }


    private void inviteUser() {
        InviteEventRequest request = new InviteEventRequest();
        request.setUserEmails(Collections.singletonList(emailAddress.getText().toString()));
        InviteEventActionMessage message = new InviteEventActionMessage();
        message.setEventId(eventId);
        message.setRequest(request);
        BusProvider.bus().post(new InviteEventAction.OnLoadingStart(message));
    };


    @Subscribe
    public void onLoadingSuccesful(InviteEventAction.OnLoadedSuccess response) {
        Log.d(TAG, "onLoadingSucessful: ");
        Intent backToEvent = new Intent(this, EventActivity.class);
        backToEvent.putExtra("eventId", eventId);
        startActivity(backToEvent);
    }

    @Subscribe
    public void onLoadingFailed(GetThumbnailAction.OnLoadingError response) {
        Log.d(TAG, "GetThumbnailAction.OnLoadingError: " + response.getCode());
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


}
