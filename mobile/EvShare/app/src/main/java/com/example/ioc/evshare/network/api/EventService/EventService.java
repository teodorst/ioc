package com.example.ioc.evshare.network.api.EventService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventService {

    @Headers("Content-Type: application/json")
    @POST("event")
    Call<CreateEventResponse> createEvent(@Header("Authorization") String token, @Body CreateEventRequest createEventRequest);


    @GET("event/{eventId}")
    Call<GetEventResponse> getEvent(@Header("Authorization") String token, @Path("eventId") Long eventId);


    @GET("events")
    Call<ListEventsResponse> getEvents(@Header("Authorization") String token);


    @GET("event/{eventId}/thumbnail/{photoId}")
    Call<ResponseBody> getThumbnail(@Header("Authorization") String token, @Path("eventId") Long eventId, @Path("photoId") Long photoId);


    @GET("event/{eventId}/photo/{photoId}")
    Call<byte[]> getPhoto(@Header("Authorization") String token, @Path("eventId") Long eventId, @Path("photoId") Long photoId);



    @POST("event/{eventId}/photo")
    Call<Void> uploadPhoto(@Header("Authorization") String token, @Path("eventId") Long eventId);


    @Headers("Content-Type: application/json")
    @POST("event/{eventId}")
    Call<Void> updateEvent(@Path("eventId") Long eventId, @Body CreateEventRequest requestBody);


    @POST("event/{eventId}/remove")
    Call<Void> removeEvent(@Path("eventId") Long eventId);

}

