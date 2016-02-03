package com.loftschool.moneytracker.rest;

import com.loftschool.moneytracker.MonyeTrackerApplication;
import com.loftschool.moneytracker.rest.model.CreateCategory;
import com.loftschool.moneytracker.rest.model.SynchCategory;
import com.loftschool.moneytracker.rest.model.SynchExpense;
import com.loftschool.moneytracker.rest.model.UserLoginModel;
import com.loftschool.moneytracker.rest.model.UserRegistrationModel;


public class RestService {

    private static final String REGISTER_FLAG = "1";
    private RestClient restClient;

    public RestService(){
        restClient = new RestClient();
    }

    public UserRegistrationModel register(String login, String password){
        return restClient.getRegisterUserApi().registerUser(login, password, REGISTER_FLAG);
    }

    public UserLoginModel login(String login, String password){
      return restClient.getLoginUserApi().loginUser(login, password);
    }

    public CreateCategory createCategory(String title){
        return restClient.getCreateCategoryApi().createCategory(title, MonyeTrackerApplication.getAuthKey());
    }

    public SynchCategory categorySynch(String data){
        return restClient.getSynchCategoryApi().categorySynch(data, MonyeTrackerApplication.getAuthKey());
    }
    public SynchExpense expenseSynch(String data){
        return restClient.getSynchExpenseApi().expenseSynch(data, MonyeTrackerApplication.getAuthKey());
    }
}
