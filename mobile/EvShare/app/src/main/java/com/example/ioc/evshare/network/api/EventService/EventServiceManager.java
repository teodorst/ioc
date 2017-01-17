package com.example.ioc.evshare.network.api.EventService;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.ioc.evshare.network.NetworkManager;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.events.CreateEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.GetThumbnailAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.ListEventsAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.UploadPhotoEventAction;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.ReceiveImageActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.user.CreateUserAction;
import com.example.ioc.evshare.network.api.UserService.UserService;
import com.example.ioc.evshare.network.api.UserService.UserServiceManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    @Subscribe void getEvent(GetEventAction.OnLoadingStart onLoadingStartMessage) {
        Call<GetEventResponse> call = eventServiceAPI.getEvent(networkManager.getToken(), onLoadingStartMessage.getMessage().getId());
        call.enqueue(new Callback<GetEventResponse>() {
            @Override
            public void onResponse(Call<GetEventResponse> call, Response<GetEventResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: GUD");
                    Log.d(TAG, "onResponse: " + response.body().getPhotosIds());
                    bus.post(new GetEventAction.OnLoadedSuccess(response.body()));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new GetEventAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<GetEventResponse> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new GetEventAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(GetEventAction.FAILED_GET_EVENT_ACTION);
                }
            }
        });
    }


    @Subscribe void getThumbnail(final GetThumbnailAction.OnLoadingStart onLoadingStartMessage) {
        Log.d(TAG, "getThumbnail: ajunge?" + onLoadingStartMessage.getMessage().getEventId());
        Call<ResponseBody> call = eventServiceAPI.getThumbnail(
                networkManager.getToken(),
                onLoadingStartMessage.getMessage().getEventId(),
                onLoadingStartMessage.getMessage().getPhotoId()
                );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: vine poza");
                if (response.isSuccessful()) {
                    ReceiveImageActionMessage actionMessage = new ReceiveImageActionMessage();
                    actionMessage.setImageId(onLoadingStartMessage.getMessage().getPhotoId());
                    try {
                        actionMessage.setImage(response.body().bytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bus.post(new GetThumbnailAction.OnLoadedSuccess(actionMessage));
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new GetThumbnailAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new GetThumbnailAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(GetThumbnailAction.FAILED_DOWNLAOD_THUMBNAIL_ACTION);
                }
            }
        });
    }


    @Subscribe void uploadPhoto(UploadPhotoEventAction.OnLoadingStart onLoadingStartMessage) {
        File file = onLoadingStartMessage.getMessage().getImageFile();
        Log.d(TAG, "uploadPhoto: " + onLoadingStartMessage.getMessage().getEventId());

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), onLoadingStartMessage.getMessage().getImageFile());

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Call<Void> call = eventServiceAPI.uploadPhoto(token, onLoadingStartMessage.getMessage().getEventId(), body);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    bus.post(new UploadPhotoEventAction.OnLoadedSuccess());
                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    bus.post(new UploadPhotoEventAction.OnLoadingError(errorBody.toString(), statusCode));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    bus.post(new UploadPhotoEventAction.OnLoadingError(error.getMessage(), -1));
                } else {
                    bus.post(UploadPhotoEventAction.FAILED_UPLOAD_PHOTO_ACTION);
                }
            }
        });
    }

}
