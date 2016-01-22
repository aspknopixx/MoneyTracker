package com.loftschool.moneytracker.rest;

import com.loftschool.moneytracker.rest.model.UserRegistrationModel;

/**
 * Created by omk on 04.01.2016.
 */
public class RestService {

    private static final String REGISTER_FLAG = "1";
    private RestClient restClient;

    public RestService(){
        restClient = new RestClient();
    }

    public UserRegistrationModel register(String login, String password){
        return restClient.getRegisterUserApi().registerUser(login, password, REGISTER_FLAG);
    }
}
