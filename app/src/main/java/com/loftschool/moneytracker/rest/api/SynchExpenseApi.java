package com.loftschool.moneytracker.rest.api;


import com.loftschool.moneytracker.rest.model.SynchExpense;

import retrofit.http.GET;
import retrofit.http.Query;

public interface SynchExpenseApi {

    @GET("/transactions/synch")
    SynchExpense expenseSynch(@Query("data") String data,
                              @Query("auth_token") String token);
}
