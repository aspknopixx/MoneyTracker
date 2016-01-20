package com.loftschool.moneytracker.ui.activities;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.rest.RestService;
import com.loftschool.moneytracker.rest.model.UserRegistrationModel;
import com.loftschool.moneytracker.util.NetworkStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_registration)
public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = "RegistrationActivity";

    @ViewById(R.id.reg_activity)
    ScrollView scrollView;

    @ViewById(R.id.input_login)
    EditText loginInput;

    @ViewById(R.id.input_password)
    EditText pwdInput;

    @ViewById(R.id.btn_login)
    Button btnLogin;

    @Click(R.id.btn_login)
    public void ButtonLoginClick(){
        if (NetworkStatus.isNetworkAvailable(this)){
            String login = loginInput.getText().toString();
            String password = pwdInput.getText().toString();
            if (login.length()<5||password.length()<5){
                Snackbar.make(scrollView, R.string.input_pwd_login, Snackbar.LENGTH_SHORT).show();
            }
            else{
                registerUser(login, password);
            }
        }
        else{
            Snackbar.make(scrollView, R.string.no_connection, Snackbar.LENGTH_SHORT).show();
        }


    }


    @AfterViews
    void ready(){

        if (btnLogin.isPressed()){
            ButtonLoginClick();
        }
    }

    @Background
    public void registerUser(String log, String pas) {
        RestService restService = new RestService();
        UserRegistrationModel userRegistrationModel = restService.register(log, pas);
        Log.i(TAG, "status: " + userRegistrationModel.getStatus() + " " + ", id: " + userRegistrationModel.getId());
        if (!userRegistrationModel.getStatus().equalsIgnoreCase("success")) {
            Snackbar.make(scrollView, "Пользователь с таким логином уже существует", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity_.class);
            this.startActivity(intent);
        }
    }
}
