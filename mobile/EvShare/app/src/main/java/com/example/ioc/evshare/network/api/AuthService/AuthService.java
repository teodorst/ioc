package com.example.ioc.evshare.network.api.AuthService;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {

    @Headers("Content-Type: application/json")
    @POST("auth")
    Call<AuthResponse> authUser(@Body AuthRequest authRequest);

}
