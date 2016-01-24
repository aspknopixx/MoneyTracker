package com.loftschool.moneytracker.rest;


import com.loftschool.moneytracker.rest.api.CreateCategoryApi;
import com.loftschool.moneytracker.rest.api.LoginUserApi;
import com.loftschool.moneytracker.rest.api.RegisterUserApi;
import com.loftschool.moneytracker.rest.model.CreateCategory;

import retrofit.RestAdapter;

public class RestClient {

    private static final String BASE_URL = "http://lmt.loftblog.tmweb.ru";

    private RegisterUserApi registerUserApi;
    private LoginUserApi loginUserApi;
    private CreateCategoryApi createCategoryApi;

    public RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        registerUserApi = restAdapter.create(RegisterUserApi.class);
        loginUserApi = restAdapter.create(LoginUserApi.class);
        createCategoryApi = restAdapter.create(CreateCategoryApi.class);
    }
    public RegisterUserApi getRegisterUserApi(){
        return registerUserApi;
    }

    public LoginUserApi getLoginUserApi() {
        return loginUserApi;
    }

    public CreateCategoryApi getCreateCategoryApi() {
        return createCategoryApi;
    }
}
