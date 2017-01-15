package com.example.ioc.evshare.network.api.EventService;

import com.example.ioc.evshare.network.NetworkManager;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.events.CreateEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.ListEventsAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.CreateUserAction;
import com.example.ioc.evshare.network.api.UserService.UserService;
import com.example.ioc.evshare.network.api.UserService.UserServiceManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventServiceManager {

    private static final String TAG = "EventServiceManager";
    private static EventServiceManager instance = null;
    private EventService eventServiceAPI;
    private Bus bus;
    private NetworkManager networkManager;
    private String token;

    private EventServiceManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.101.218.125:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        eventServiceAPI = retrofit.create(EventService.class);

        // register to event bus
        bus = BusProvider.bus();
        bus.register(this);

        networkManager = NetworkManager.getInstance();
        token = networkManager.getToken();

    }

    public static EventServiceManager getInstance() {
        if (instance == null) {
            instance = new EventServiceManager();
        }

        return instance;
    }

    @Subscribe void listEvents(ListEventsAction.OnLoadingStart onLoadingStartMessage) {
        Call<ListEventsResponse> call = eventServiceAPI.getEvents(networkManager.getToken());
        call.enqueue(new Callback<ListEventsResponse>() {
            @Override
            public void onResponse(Call<ListEventsResponse> call, Response<ListEventsResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new ListEventsAction.OnLoadedSuccess(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new ListEventsAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<ListEventsResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new ListEventsAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(ListEventsAction.FAILED_LIST_EVENTS_ACTION);
                }
            }
        });
    }


    @Subscribe void createEvents(CreateEventAction.OnLoadingStart onloadingStartMessage) {
        Call<CreateEventResponse> call = eventServiceAPI.createEvent(token, onloadingStartMessage.getMessage().getRequestBody());
        call.enqueue(new Callback<CreateEventResponse>() {
            @Override
            public void onResponse(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new CreateEventAction.OnLoadedSuccess(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new CreateEventAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<CreateEventResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new CreateEventAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(CreateUserAction.FAILED_CREATE_USER_EVENT);
                }
            }
        });
    }

}
