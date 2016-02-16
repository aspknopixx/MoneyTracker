package com.loftschool.moneytracker.ui.activities;


import android.accounts.AccountManager;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;


import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.loftschool.moneytracker.MonyeTrackerApplication;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.rest.RestClient;
import com.loftschool.moneytracker.rest.model.GoogleTokenStatusModel;
import com.loftschool.moneytracker.util.ConstantString;
import com.loftschool.moneytracker.util.NetworkStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity{

    private String gToken;
    private RestClient restClient;

    String token;


    @ViewById(R.id.splash_activity)
    RelativeLayout relativeLayout;

    @AfterViews
    void ready(){

        restClient = new RestClient();
        gToken = MonyeTrackerApplication.getGoogleToken(this);
        if (NetworkStatus.isNetworkAvailable(this)) {
            if (gToken.equalsIgnoreCase("2")) {
                startActivity();
            } else {
                checkTokenValid();
            }
        }else {
            Snackbar.make(relativeLayout, R.string.no_connection, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Background
    void checkTokenValid() {
        restClient.getCheckGoogleTokenApi().tokenStatus(gToken, new Callback<GoogleTokenStatusModel>() {
            @Override
            public void success(GoogleTokenStatusModel googleTokenStatusModel, Response response) {
                Log.e("LOG_TAG", googleTokenStatusModel.getStatus());
                if (googleTokenStatusModel.getStatus().equalsIgnoreCase(ConstantString.ERROR)){
                    doubleTokenEx();
                } else{
                    Intent regIntent = new Intent(SplashActivity.this, MainActivity_.class);
                    startActivity(regIntent);
                    finish();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void doubleTokenEx(){
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
        startActivityForResult(intent, 123);
    }

    @Background
    void getToken(Intent data){
        final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String token = null;
        try {
            token = GoogleAuthUtil.getToken(SplashActivity.this, accountName, LoginActivity.SCOPES);
        }catch (UserRecoverableAuthException userAuthEx){
            startActivityForResult(userAuthEx.getIntent(), 10);
        }
        catch (IOException inEx) {
            inEx.printStackTrace();
        } catch (GoogleAuthException fatalAuthEx) {
            Log.e("LOG_TAG", "Fatal Exception: " + fatalAuthEx.getLocalizedMessage());
        }

        MonyeTrackerApplication.setGoogleToken(this, token);
        Log.e("LOG_TAG", "GOOGLE_TOKEN:" + " " + MonyeTrackerApplication.getGoogleToken(this));

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && requestCode == RESULT_OK){
            getToken(data);
        }
    }

    @Background(delay = 1300)
    void startActivity(){
        token = MonyeTrackerApplication.getAuthKey();
        if (token.equals("")){
            startActivity(new Intent(this, LoginActivity_.class));
        }
        else{
            startActivity(new Intent(this, MainActivity_.class));
        }

    }
}
