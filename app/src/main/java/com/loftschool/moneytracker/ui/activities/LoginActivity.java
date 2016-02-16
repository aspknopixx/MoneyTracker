package com.loftschool.moneytracker.ui.activities;


import android.accounts.AccountManager;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.UserRecoverableNotifiedException;
import com.google.android.gms.common.AccountPicker;
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

import java.io.IOException;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity{
    private final static String G_PLUS_SCOPE =
            "oauth2:https://www.googleapis.com/auth/plus.me";
    private final static String USERINFO_SCOPE =
            "https://www.googleapis.com/auth/userinfo.profile";
    private final static String EMAIL_SCOPE =
            "https://www.googleapis.com/auth/userinfo.email";
    public final static String SCOPES = G_PLUS_SCOPE + " " + USERINFO_SCOPE + " " + EMAIL_SCOPE;


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

    @Click(R.id.google_signIn_button)
    void btnGplusLogin(){
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK){
            getToken(data);
        }
    }

    @Background
     void getToken(Intent data){
        final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String token = null;
        try {
             token = GoogleAuthUtil.getToken(LoginActivity.this, accountName, SCOPES);
        }catch (UserRecoverableAuthException userAuthEx){
            startActivityForResult(userAuthEx.getIntent(), 10);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        } catch (GoogleAuthException fatalAuthEx) {
            Log.e(LOG_TAG, "Fatal Exception: " + fatalAuthEx.getLocalizedMessage());
        }

        MonyeTrackerApplication.setGoogleToken(this, token);
        Log.e(LOG_TAG,"GOOGLE_TOKEN:" + " " + MonyeTrackerApplication.getGoogleToken(this));
        if (!MonyeTrackerApplication.getGoogleToken(this).equalsIgnoreCase("2")){
            Intent regIntent = new Intent(LoginActivity.this, MainActivity_.class);
            startActivity(regIntent);
            finish();
        }
    }

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
        this.startActivity(intent);
    }

    @Background
    void login(String login, String password){
        RestService restService = new RestService();
        UserLoginModel userLoginModel = restService.login(login, password);
        switch (userLoginModel.getStatus()){

            case ConstantString.SUCCESS:
                MonyeTrackerApplication.setAuthToken(userLoginModel.getAuthToken());
//                MoneyTrackerApplication.setAuthToken(userLoginModel.getAuthToken());
                Log.d(LOG_TAG, "Status: " + userLoginModel.getStatus() + ", token: " + MonyeTrackerApplication.getAuthKey());
                Intent intent = new Intent(this, MainActivity_.class);
                this.startActivity(intent);
                break;

            case ConstantString.WRONG_PASSWORD:
                Snackbar.make(scrollView, R.string.wrong_pwd, Snackbar.LENGTH_SHORT).show();
                break;

            case ConstantString.WRONG_LOGIN:
                Snackbar.make(scrollView, R.string.wrong_login, Snackbar.LENGTH_SHORT).show();
                break;

            case ConstantString.ERROR:
                Snackbar.make(scrollView, R.string.error_text,Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
