package com.loftschool.moneytracker.rest.api;

import com.loftschool.moneytracker.rest.model.SynchCategory;

import retrofit.http.GET;
import retrofit.http.Query;


public interface SynchCategoryApi {

    @GET("/categories/synch")
    SynchCategory categorySynch(@Query("google_token") String gToken,@Query("data") String data,
                            @Query("auth_token") String token);
}
