package com.loftschool.moneytracker.rest.api;

import com.loftschool.moneytracker.rest.model.GooglePlusModel;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by aspknopixx on 15.02.16.
 */
public interface CheckGoogleJsonApi {

    @GET("/gjson")
    GooglePlusModel gJson(@Query("google_token") String gToken);
}
