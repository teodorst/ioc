package com.example.ioc.evshare.network.api.EventService;

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
    Call<CreateEventResponse> createEvent(@Body CreateEventRequest createEventRequest);

    @GET("event/{eventId}")
    Call<GetEventResponse> getEvent(@Path("eventId") String eventId);

    @GET("events")
    Call<ListEventsResponse> getEvents(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("event/{eventId}")
    Call<Void> updateEvent(@Path("eventId") String eventId, @Body CreateEventRequest requestBody);

    @POST("event/{eventId}/remove")
    Call<Void> removeEvent(@Path("eventId") String eventId);

}

