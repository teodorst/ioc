package com.example.ioc.evshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.events.CreateEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.CreateEventActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.user.CreateUserAction;
import com.example.ioc.evshare.network.api.EventService.CreateEventRequest;
import com.example.ioc.evshare.network.api.EventService.CreateEventResponse;
import com.squareup.otto.Subscribe;

public class CreateEventActivity extends AppCompatActivity {
    private static final String TAG = "CreateUserActivity";

    private EditText eventNameEditText;
    private EditText eventLocationEditText;
    private EditText eventDateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // connect UI
        eventNameEditText = (EditText) findViewById(R.id.create_event_name_input);
        eventLocationEditText = (EditText) findViewById(R.id.create_event_location_input);
        eventDateEditText = (EditText) findViewById(R.id.create_event_date_input);

    }

    private void createEvent() {
        CreateEventActionMessage actionMessage = new CreateEventActionMessage();
        CreateEventRequest createEventRequest = new CreateEventRequest();
        createEventRequest.setName(eventNameEditText.getText().toString());
        createEventRequest.setDate(eventDateEditText.getText().toString());
        createEventRequest.setLocation(eventLocationEditText.getText().toString());
        actionMessage.setRequestBody(createEventRequest);
        BusProvider.bus().post(new CreateEventAction.OnLoadingStart(actionMessage));
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
    public void onLoadingSuccesful(CreateEventAction.OnLoadedSuccess response) {
        Log.d(TAG, "onLoadingSuccessful: " + response.toString());
        Intent eventActivityIntent = new Intent(this, LoginActivity.class);
        eventActivityIntent.putExtra("eventId", response.getResponse().getId());
        startActivity(eventActivityIntent);
    }

    @Subscribe
    public void onLoadingFailed(CreateUserAction.OnLoadingError response) {
        Log.d(TAG, "onLoadingError: " + response.getCode());
    }

}
