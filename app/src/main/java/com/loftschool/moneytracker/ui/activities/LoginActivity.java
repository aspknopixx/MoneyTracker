package com.loftschool.moneytracker.ui.activities;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loftschool.moneytracker.MonyeTrackerApplication;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.rest.RestService;
import com.loftschool.moneytracker.rest.model.CreateCategory;
import com.loftschool.moneytracker.rest.model.UserLoginModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity{

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    @AfterViews
    void ready(){
        login();
    }

    @Background
    void login(){
        RestService restService = new RestService();
        UserLoginModel userLoginModel = restService.login("userasp6","666");
        MonyeTrackerApplication.setAuthToken(userLoginModel.getAuthToken());
        Log.d(LOG_TAG, "Status: " + userLoginModel.getStatus() + ", token: " + MonyeTrackerApplication.getAuthKey());


        CreateCategory createCategory = restService.createCategory("Test1");
        Log.d(LOG_TAG, "Status: " + createCategory.getStatus() + ", title: " + createCategory.getData().getTitle()+
        ", Id: " + createCategory.getData().getId());

    }
}
