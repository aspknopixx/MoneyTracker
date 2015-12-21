package com.loftschool.moneytracker;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

public class MonyeTrackerApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
