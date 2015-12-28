package com.loftschool.moneytracker.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.database.Categories;
import com.loftschool.moneytracker.ui.fragments.CategoryFragment_;
import com.loftschool.moneytracker.ui.fragments.ExpensesFragment_;
import com.loftschool.moneytracker.ui.fragments.SettingsFragment_;
import com.loftschool.moneytracker.ui.fragments.StatisticsFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_view)
    NavigationView navigationView;

    @InstanceState
    Bundle savedInstanceState;

    @AfterViews
    void ready() {

        setupToolbar();
        setupDrawer();
        if (new Select().from(Categories.class).execute().size() == 0){
            createFakeCategories();
        }

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ExpensesFragment_()).commit();
        }

    }

    private void setupToolbar()
    {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();//check the import android.support.v7.app.ActionBar
        if(actionBar != null)
        {
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OptionsItem(android.R.id.home)
    void openDrawerByButton() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Menu menuItems = navigationView.getMenu();
        Fragment findingFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        if (findingFragment != null && findingFragment instanceof ExpensesFragment_) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            navigationView.setCheckedItem(R.id.drawer_expenses);
        } else if (findingFragment instanceof CategoryFragment_) {
            navigationView.setCheckedItem(R.id.drawer_categories);
        } else if (findingFragment instanceof StatisticsFragment_) {
            navigationView.setCheckedItem(R.id.drawer_statistics);
        } else if (findingFragment instanceof SettingsFragment_) {
            navigationView.setCheckedItem(R.id.drawer_settings);
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupDrawer()
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.drawer_expenses:
                        fragment = new ExpensesFragment_();
                        break;
                    case R.id.drawer_categories:
                        fragment = new CategoryFragment_();
                        break;
                    case R.id.drawer_statistics:
                        fragment = new StatisticsFragment_();
                        break;
                    case R.id.drawer_settings:
                        fragment = new SettingsFragment_();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }


    // создем категории

    private void createFakeCategories(){
        Categories categoryClothes = new Categories("Clothes");
        categoryClothes.save();
        Categories categoryFood = new Categories("Food");
        categoryFood.save();
        Categories categoryHouse = new Categories("House");
        categoryHouse.save();
        Categories categoryCar = new Categories("Car");
        categoryCar.save();
        Categories categoryHobby = new Categories("Hobby");
        categoryHobby.save();
        Categories categoryFun = new Categories("Fun");
        categoryFun.save();
    }
}

