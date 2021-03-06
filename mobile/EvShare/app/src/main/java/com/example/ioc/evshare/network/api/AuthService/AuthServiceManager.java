package com.example.ioc.evshare.network.api.AuthService;

import android.util.Log;

import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.AuthAction;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthServiceManager {
    private static final String TAG = "AuthServiceManager";
    private AuthService authApi;
    private Bus bus;
    private static AuthServiceManager instance = null;

    private AuthServiceManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.101.218.125:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authApi = retrofit.create(AuthService.class);

        // register to event bus
        bus = BusProvider.bus();
        bus.register(this);

    }

    public static AuthServiceManager getInstance() {
        if (instance == null) {
            instance = new AuthServiceManager();
        }
        return instance;
    }

    @Subscribe
    public void authUser(AuthAction.OnLoadingStart onLoadingStart) {

        Call<AuthResponse> authUserCall = authApi.authUser(onLoadingStart.getMessage());
        authUserCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    bus.post(new AuthAction.OnLoadedSuccess(response.body().getToken()));
                }
                else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new AuthAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    Log.d(TAG, "onFailure: " + error.getMessage());
                    bus.post(new AuthAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(AuthAction.FAILED);
                }
            }
        });
    }


}
