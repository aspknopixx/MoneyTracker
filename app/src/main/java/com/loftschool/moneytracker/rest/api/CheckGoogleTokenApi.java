package com.loftschool.moneytracker.rest.api;

import com.loftschool.moneytracker.rest.model.GoogleTokenStatusModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CheckGoogleTokenApi {
    @GET("/gcheck")
    void tokenStatus(@Query("google_token") String gToken, Callback<GoogleTokenStatusModel> modelCallback);


    @GET("/gjson")
    GoogleTokenStatusModel googleJson(@Query("google_token") String gToken);
}
