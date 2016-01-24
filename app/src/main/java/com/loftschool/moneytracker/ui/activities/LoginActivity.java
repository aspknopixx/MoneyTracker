package com.loftschool.moneytracker.ui.activities;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.loftschool.moneytracker.MonyeTrackerApplication;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.rest.RestService;
import com.loftschool.moneytracker.rest.model.CreateCategory;
import com.loftschool.moneytracker.rest.model.UserLoginModel;
import com.loftschool.moneytracker.util.ConstantString;
import com.loftschool.moneytracker.util.NetworkStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity{

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    @ViewById(R.id.login_activity)
    ScrollView scrollView;

    @ViewById(R.id.input_login_acLogin)
    EditText loginInput;

    @ViewById(R.id.input_password_acLogin)
    EditText pwd;

    @ViewById(R.id.btn_login_acLogin)
    Button btnLogin;

    @ViewById(R.id.link_reg_acLogin)
    TextView regLogin;


    @Click(R.id.btn_login_acLogin)
    void BtnLoginClicked(){
        if (NetworkStatus.isNetworkAvailable(this)){
            String login = loginInput.getText().toString();
            String password = pwd.getText().toString();
            if (login.length()<5||password.length()<5){
                Snackbar.make(scrollView, R.string.input_pwd_login, Snackbar.LENGTH_SHORT).show();
            }
            else {
                login(login, password);
            }
        }
        else{
            Snackbar.make(scrollView, R.string.no_connection, Snackbar.LENGTH_SHORT).show();
        }
    }


    @Click(R.id.link_reg_acLogin)
    void newRegLogin(){
        Intent intent = new Intent(this, RegistrationActivity_.class);
        intent.putExtra("key", "value");
        this.startActivity(intent);
    }

    @Background
    void login(String login, String password){
        RestService restService = new RestService();
        UserLoginModel userLoginModel = restService.login(login, password);
        switch (userLoginModel.getStatus()){

            case ConstantString.success:
                MonyeTrackerApplication.setAuthToken(userLoginModel.getAuthToken());
//                MoneyTrackerApplication.setAuthToken(userLoginModel.getAuthToken());
                Log.d(LOG_TAG, "Status: " + userLoginModel.getStatus() + ", token: " + MonyeTrackerApplication.getAuthKey());
                Intent intent = new Intent(this, MainActivity_.class);
                intent.putExtra("key", "value");
                this.startActivity(intent);
                break;

            case ConstantString.wrongPassword:
                Snackbar.make(scrollView, R.string.wrong_pwd, Snackbar.LENGTH_SHORT).show();
                break;

            case ConstantString.wrongLogin:
                Snackbar.make(scrollView, R.string.wrong_login, Snackbar.LENGTH_SHORT).show();
                break;

            case ConstantString.error:
                Snackbar.make(scrollView, R.string.error_text,Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
