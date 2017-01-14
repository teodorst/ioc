package com.example.ioc.evshare.network.api.UserService;

import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.user.CreateUserAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.GetUserAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.ListUsersAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.message.ListUsersActionMessage;
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
                .baseUrl("http://46.101.218.125:8080/")
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
    public void createUser(CreateUserAction.OnLoadingStart onLoadingStartMessage) {
        Call<Void> call = userServiceAPI.createUser(onLoadingStartMessage.getMessage().getRequestBody());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    bus.post(new CreateUserAction.OnLoadedSuccess());
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new CreateUserAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new CreateUserAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(CreateUserAction.FAILED_CREATE_USER_EVENT);
                }
            }
        });
    }

    @Subscribe
    public void getUser(GetUserAction.OnLoadingStart onLoadingStartMessage) {
        Call<GetUserResponse> call = userServiceAPI.getUser(onLoadingStartMessage.getMessage().getId());
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new GetUserAction.OnLoadingSuccessful(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new GetUserAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new CreateUserAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(CreateUserAction.FAILED_CREATE_USER_EVENT);
                }
            }
        });
    }

    @Subscribe
    public void listUsers(ListUsersAction.OnLoadingStart onLoadingStartMessage) {
        ListUsersActionMessage message = onLoadingStartMessage.getMessage();
        Call<ListUsersResponse> call = userServiceAPI.listUsers(message.getPageNumber(), message.getPageSize(),
                message.getSortBy(), message.getSortOrder());
        call.enqueue(new Callback<ListUsersResponse>() {
            @Override
            public void onResponse(Call<ListUsersResponse> call, Response<ListUsersResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new ListUsersAction.OnLoadingSuccessful(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new ListUsersAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<ListUsersResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new ListUsersAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(ListUsersAction.FAILED_LIST_USER_EVENT);
                }
            }
        });
    }


}


