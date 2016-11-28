package com.example.ioc.evshare.network.api.UserService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @Headers("Content-Type: application/json")
    @POST("user/register")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest createUserRequest);


    @Headers("Content-Type: application/json")
    @GET("user/{userId}")
    Call<GetUserResponse> getUser(@Path("userId") String userId);


    @Headers("Content-Type: application/json")
    @GET("users?page={pageNumber}&pageSize={pageSize}&sortBy={sortBy}&sortOrder={sortOrder}")
    Call<ListUsersResponse> listUsers(@Path("pageNumber") int pageNumber, @Path("pageSize") int pageSize, @Path("sortBy") String sortBy,
                                      @Path("sortOrder") String sortOrder);

}
