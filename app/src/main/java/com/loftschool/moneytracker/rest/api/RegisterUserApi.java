package com.loftschool.moneytracker.rest.api;


import com.loftschool.moneytracker.rest.model.UserRegistrationModel;

import retrofit.http.GET;
import retrofit.http.Query;

public interface RegisterUserApi {

    @GET("/auth")
    UserRegistrationModel registerUser(@Query("login") String login,
                                       @Query("password") String password,
                                       @Query("register") String register);
}
