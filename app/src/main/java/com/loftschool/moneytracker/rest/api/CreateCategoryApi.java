package com.loftschool.moneytracker.rest.api;


import com.loftschool.moneytracker.rest.model.CreateCategory;

import retrofit.http.GET;
import retrofit.http.Query;

public interface CreateCategoryApi {

    @GET("/categories/add")
    CreateCategory createCategory(@Query("title") String title,
                                  @Query("auth_token") String token);
}
