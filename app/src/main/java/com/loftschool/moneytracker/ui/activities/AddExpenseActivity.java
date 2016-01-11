package com.loftschool.moneytracker.ui.activities;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.activeandroid.query.Select;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.adapters.SpinAdapter;
import com.loftschool.moneytracker.database.Categories;
import com.loftschool.moneytracker.database.Expenses;
import com.loftschool.moneytracker.rest.RestService;
import com.loftschool.moneytracker.rest.model.UserRegistrationModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@EActivity(R.layout.activity_add_expense)
public class AddExpenseActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddExpenseActivity.class.getSimpleName();

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
      //  RestService();
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


        TextView date = (TextView) findViewById(R.id.tv_date);
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        date.setText(new StringBuilder().append(dd).append(" ").append("-").append(mm + 1).append("-")
                .append(yy));
    }

//    @Background
//    public void RestService(){
//        RestService restService = new RestService();
//        UserRegistrationModel userRegistrationModel = restService.register("user666", "test23");
//        Log.e(LOG_TAG, "status" + userRegistrationModel.getStatus() + ", id" + userRegistrationModel.getId());
//    }

    @Click(R.id.fab_expense)
    public void clickBtn(){
        EditText sum = (EditText)findViewById(R.id.sum_text);
        EditText fill = (EditText)findViewById(R.id.fill_text);
//      EditText date = (EditText)findViewById(R.id.date_text);
        TextView date = (TextView) findViewById(R.id.tv_date);

        if(sum.getText().toString().equals("") || fill.getText().toString().equals("")){
            Toast.makeText(this, "Не заполнено одно из обязательных полей", Toast.LENGTH_LONG).show();
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

