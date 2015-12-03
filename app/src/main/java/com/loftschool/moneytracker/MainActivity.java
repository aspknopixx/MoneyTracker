package com.loftschool.moneytracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        Log.d(LOG_TAG,"onCreate");
    }

    public View onCreateView(String name, Context context, AttributeSet attrs){
        Log.d(LOG_TAG,"onCreateView()");
        return super.onCreateView(name, context, attrs);
    }

    protected void onStart(){
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }
    protected void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG,"onRestart");
    }
    protected void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"onResume");
    }
    protected void onPause(){
        super.onPause();
        Log.d(LOG_TAG,"onPause");
    }
    protected void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }
    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
