package com.loftschool.moneytracker.ui.activities;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;


import com.activeandroid.query.Select;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.adapters.SpinAdapter;
import com.loftschool.moneytracker.database.Categories;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EActivity(R.layout.activity_add_expense)
public class AddExpenseActivity extends AppCompatActivity {
    @ViewById
    protected Toolbar toolbar;

    @OptionsItem(R.id.home)
    void back() {
        onBackPressed();
        finish();
    }

    @AfterViews
    void ready() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                setTitle("Добавить трату");

                SpinAdapter adapter = new SpinAdapter(this, getDataList());
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                spinner.setAdapter(adapter);
            }
    }

    private List<Categories> getDataList()
    {
        return new Select()
                .from(Categories.class)
                .execute();
    }
}

