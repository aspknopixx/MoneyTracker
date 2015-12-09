package com.loftschool.moneytracker;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
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
    private Fragment fragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupDrawer();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ExpensesFragment()).commit();
        }

        Log.d(LOG_TAG, "onCreate");
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
/*Сделать так, что бы при навигации по бэкстэку перемещался указатель активного экрана.*/
    public void onBackPressed() {
         int itemId;
//Проверяем открыт ли наш navigationView, если да то закрываем его.
        if(drawerLayout.isDrawerOpen(navigationView)){
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
        Fragment findingFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (findingFragment != null ){
             itemId = R.id.drawer_expenses;
              if (findingFragment instanceof ExpensesFragment){
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                itemId = R.id.drawer_expenses;
            } if(findingFragment instanceof CategoryFragment) {
                itemId = R.id.drawer_categories;
            } if(findingFragment instanceof StatisticsFragment) {
                itemId = R.id.drawer_statistics;
            } if(findingFragment instanceof SettingsFragment) {
                itemId = R.id.action_settings;
            }
            navigationView.getMenu().findItem(itemId).setChecked(true);
        }}

    private void setupDrawer(){
       drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(MenuItem item) {

           switch (item.getItemId()){
                   case  R.id.drawer_expenses:
                       fragment = new ExpensesFragment();
                       break;
                   case R.id.drawer_categories:
                       fragment = new CategoryFragment();
                       break;
                   case R.id.drawer_statistics:
                       fragment = new StatisticsFragment();
                       break;
                   case R.id.drawer_settings:
                       fragment = new SettingsFragment();
                       break;
               }
               getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
               item.setChecked(true);
               drawerLayout.closeDrawers();
               return false;
           }
       });
    }

    // нажатие на сендвич
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
