package com.example.ioc.evshare.network.api.UserService;

import com.example.ioc.evshare.network.api.AuthService.AuthService;
import com.example.ioc.evshare.network.eventsBus.BusProvider;
import com.example.ioc.evshare.network.eventsBus.events.user.CreateUserEvent;
import com.example.ioc.evshare.network.eventsBus.events.user.GetUserEvent;
import com.example.ioc.evshare.network.eventsBus.events.user.ListUsersEvent;
import com.example.ioc.evshare.network.eventsBus.events.user.message.ListUsersEventMessage;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserServiceManager {

    private static final String TAG = "UserServiceManager";
    private static UserServiceManager instance = null;
    private UserService userServiceAPI;
    private Bus bus;

    private UserServiceManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-84af3-evshare.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userServiceAPI = retrofit.create(UserService.class);

        // register to event bus
        bus = BusProvider.bus();
        bus.register(this);
    }

    public static UserServiceManager getInstance() {
        if (instance == null) {
            instance = new UserServiceManager();
        }
        return instance;
    }

    @Subscribe
    public void createUser(CreateUserEvent.OnLoadingStart onLoadingStartMessage) {
        Call<CreateUserResponse> call = userServiceAPI.createUser(onLoadingStartMessage.getMessage().getRequestBody());
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new CreateUserEvent.OnLoadedSuccess(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new CreateUserEvent.OnLoadingError(errorBody.toString(), statusCode));
                }

            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new CreateUserEvent.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(CreateUserEvent.FAILED_CREATE_USER_EVENT);
                }
            }
        });
    }

    @Subscribe
    public void getUser(GetUserEvent.OnLoadingStart onLoadingStartMessage) {
        Call<GetUserResponse> call = userServiceAPI.getUser(onLoadingStartMessage.getMessage().getId());
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new GetUserEvent.OnLoadingSuccessful(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new GetUserEvent.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new CreateUserEvent.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(CreateUserEvent.FAILED_CREATE_USER_EVENT);
                }
            }
        });
    }

    @Subscribe
    public void listUsers(ListUsersEvent.OnLoadingStart onLoadingStartMessage) {
        ListUsersEventMessage message = onLoadingStartMessage.getMessage();
        Call<ListUsersResponse> call = userServiceAPI.listUsers(message.getPageNumber(), message.getPageSize(),
                message.getSortBy(), message.getSortOrder());
        call.enqueue(new Callback<ListUsersResponse>() {
            @Override
            public void onResponse(Call<ListUsersResponse> call, Response<ListUsersResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new ListUsersEvent.OnLoadingSuccessful(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new ListUsersEvent.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<ListUsersResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new ListUsersEvent.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(ListUsersEvent.FAILED_LIST_USER_EVENT);
                }
            }
        });
    }


}


