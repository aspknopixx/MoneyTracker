package com.loftschool.moneytracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private CoordinatorLayout coordinatorContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorContainer = (CoordinatorLayout) findViewById(R.id.coordinator_container);
        setupToolbar();
        setupDrawer();

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
    private void setupDrawer(){
       drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(MenuItem item) {
               Snackbar.make(coordinatorContainer, item.getTitle(), Snackbar.LENGTH_SHORT).show();
               item.setChecked(true);
               drawerLayout.closeDrawers();
               return false;
           }
       });
    }

}
