package com.loftschool.moneytracker.ui.activities;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.activeandroid.query.Select;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.adapters.SpinAdapter;
import com.loftschool.moneytracker.database.Categories;
import com.loftschool.moneytracker.database.Expenses;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EActivity(R.layout.activity_add_expense)
public class AddExpenseActivity extends AppCompatActivity {
    @ViewById
    protected Toolbar toolbar;

    @ViewById
    Spinner spinner;

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
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
    }

    @Click(R.id.add_btn_expense)
    public void clickBtn(){
        EditText sum = (EditText)findViewById(R.id.sum_text);
        EditText fill = (EditText)findViewById(R.id.fill_text);
        EditText date = (EditText)findViewById(R.id.date_text);

        if(sum.getText().toString().equals("") || fill.getText().toString().equals("") || date.getText().toString().equals("")){
            Toast.makeText(this, "Не заполнено одно из полей", Toast.LENGTH_LONG).show();
        } else {
            Categories category = (Categories) spinner.getSelectedItem();
            Expenses expenses = new Expenses(sum.getText().toString(),fill.getText().toString(),date.getText().toString(),category);
            expenses.save();
            back();
        }
    }

    private List<Categories> getDataList()
    {
        return new Select()
                .from(Categories.class)
                .execute();
    }
}

