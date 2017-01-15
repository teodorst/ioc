package com.example.ioc.evshare.network.api.UserService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @Headers("Content-Type: application/json")
    @POST("user")
    Call<Void> createUser(@Body CreateUserRequest createUserRequest);


    @Headers("Content-Type: application/json")
    @GET("user/{userId}")
    Call<GetUserResponse> getUser(@Path("userId") String userId);


    @Headers("Content-Type: application/json")
    @GET("users")
    Call<ListUsersResponse> listUsers(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize, @Query("sortBy") String sortBy,
                                      @Query("sortOrder") String sortOrder);

}
