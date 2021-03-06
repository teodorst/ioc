package com.example.ioc.evshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.adapters.EventsListAdapter;
import com.example.ioc.evshare.listeners.EndlessScrollListener;
import com.example.ioc.evshare.model.Event;
import com.example.ioc.evshare.network.NetworkManager;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.AuthAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.ListEventsAction;
import com.example.ioc.evshare.network.api.EventService.EventServiceManager;
import com.example.ioc.evshare.network.api.EventService.GetEventResponse;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final static String TAG = "EventsActivity";

    private ListView eventsListView;
    private NetworkManager networkManager;
    private List<Event> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToCreateEventActivity();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // connect to network manager
        networkManager = NetworkManager.getInstance();
        EventServiceManager eventManager = EventServiceManager.getInstance();


        // get authToken
        Intent intent = getIntent();
        String authToken = intent.getStringExtra("AUTH_TOKEN");

        // connect to UI
        eventsListView = (ListView) findViewById(R.id.events_list);
        configureEventsList();
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void configureEventsList() {
        // set adapter
        events = getEvents();
        eventsListView.setAdapter(new EventsListAdapter(this, R.layout.events_list_item, events));
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                switchToEventActivity(events.get(position).getId());
            }
        });
    }


    @Subscribe public void onLoadingSuccesful(ListEventsAction.OnLoadedSuccess response) {
        Log.d(TAG, "onLoadingSuccessful: " + response.getResponse().getEvents());
        List<Event> events = new ArrayList<Event>();
        for (GetEventResponse eventResponse : response.getResponse().getEvents()) {
            events.add(convertGetEventResponseToEvent(eventResponse));
        }
        EventsListAdapter adapter = (EventsListAdapter) eventsListView.getAdapter();
        adapter.addAll(events);
    }

    @Subscribe public void onLoadingFailed(ListEventsAction.OnLoadingError response) {
        Log.d(TAG, "onLoadingError: " + response.getErrorMessage());
    }



    private Event convertGetEventResponseToEvent(GetEventResponse response) {
        return new Event(response.getId(), response.getName(), response.getLocation(), response.getDate(), response.getOwnerEmail(), response.getPhotosIds(), response.getPhotosCount());
    }

    private List<Event> getEvents() {
        // test

        Log.d(TAG, "getEventsForPage: Loading new Data!!");

        BusProvider.bus().post(new ListEventsAction.OnLoadingStart());

        return new ArrayList<Event>();
    }


    // switch to other activities
    private void switchToCreateEventActivity() {

        Intent switchToCreateActivity = new Intent(this, CreateEventActivity.class);
        startActivity(switchToCreateActivity);

    }

    private void switchToEventActivity(Long eventId) {
        Intent switchToEventActivity = new Intent(this, EventActivity.class);
        switchToEventActivity.putExtra("eventId", eventId);
        startActivity(switchToEventActivity);
    }


}
