package com.loftschool.moneytracker.ui.activities;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;


import com.loftschool.moneytracker.MonyeTrackerApplication;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.util.NetworkStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity{
    String token;


    @ViewById(R.id.splash_activity)
    RelativeLayout relativeLayout;

    @AfterViews
    void ready(){
        if (NetworkStatus.isNetworkAvailable(this)){
            startActivity();
        }
        else {
            Snackbar.make(relativeLayout, R.string.no_connection, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Background(delay = 1500)
    void startActivity(){
        token = MonyeTrackerApplication.getAuthKey();
        if (token.equals("")){
            startActivity(new Intent(this, LoginActivity_.class));
        }
        else{
            startActivity(new Intent(this, MainActivity_.class));
        }

    }
}
