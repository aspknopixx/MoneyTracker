package com.loftschool.moneytracker.rest;


import com.loftschool.moneytracker.rest.api.CheckGoogleJsonApi;
import com.loftschool.moneytracker.rest.api.CheckGoogleTokenApi;
import com.loftschool.moneytracker.rest.api.CreateCategoryApi;
import com.loftschool.moneytracker.rest.api.LoginUserApi;
import com.loftschool.moneytracker.rest.api.RegisterUserApi;
import com.loftschool.moneytracker.rest.api.SynchCategoryApi;
import com.loftschool.moneytracker.rest.api.SynchExpenseApi;
import com.loftschool.moneytracker.rest.model.CreateCategory;
import com.loftschool.moneytracker.util.ConstantString;

import retrofit.RestAdapter;

public class RestClient {

    private static final String BASE_URL = ConstantString.BASE_URL;

    private RegisterUserApi registerUserApi;
    private LoginUserApi loginUserApi;
    private CreateCategoryApi createCategoryApi;
    private SynchCategoryApi synchCategoryApi;
    private SynchExpenseApi synchExpenseApi;
    private CheckGoogleTokenApi checkGoogleTokenApi;
    private CheckGoogleJsonApi checkGoogleJsonApi;

    public RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        registerUserApi = restAdapter.create(RegisterUserApi.class);
        loginUserApi = restAdapter.create(LoginUserApi.class);
        createCategoryApi = restAdapter.create(CreateCategoryApi.class);
        synchCategoryApi = restAdapter.create(SynchCategoryApi.class);
        synchExpenseApi = restAdapter.create(SynchExpenseApi.class);
        checkGoogleTokenApi = restAdapter.create(CheckGoogleTokenApi.class);
        checkGoogleJsonApi = restAdapter.create(CheckGoogleJsonApi.class);

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
    public SynchCategoryApi getSynchCategoryApi(){
        return synchCategoryApi;
    }
    public SynchExpenseApi getSynchExpenseApi(){
        return synchExpenseApi;
    }
    public CheckGoogleTokenApi getCheckGoogleTokenApi(){
        return checkGoogleTokenApi;
    }
    public CheckGoogleJsonApi getCheckGoogleJsonApi(){return  checkGoogleJsonApi;}
}
