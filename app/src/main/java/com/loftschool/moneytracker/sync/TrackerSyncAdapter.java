package com.loftschool.moneytracker.sync;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.loftschool.moneytracker.MonyeTrackerApplication;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.database.Categories;
import com.loftschool.moneytracker.database.Expenses;
import com.loftschool.moneytracker.rest.RestService;
import com.loftschool.moneytracker.rest.model.CreateExpense;
import com.loftschool.moneytracker.rest.model.Data;
import com.loftschool.moneytracker.rest.model.SynchCategory;
import com.loftschool.moneytracker.rest.model.SynchExpense;
import com.loftschool.moneytracker.util.ConstantString;

import java.util.ArrayList;
import java.util.List;


public class TrackerSyncAdapter extends AbstractThreadedSyncAdapter{

    private static final String LOG_TAG = TrackerSyncAdapter.class.getSimpleName();

    public TrackerSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        Log.e(LOG_TAG, "Syncing method wass called!");

        categorySyncServer();
        if(!getExpensesList().isEmpty()){
            expensesSyncServer();
        }
    }
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED,
                true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL,
                true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }
    public static Account getSyncAccount(Context context) {
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        Account newAccount = new Account(context.getString(R.string.app_name),
                context.getString(R.string.sync_account_type));
        if ( null == accountManager.getPassword(newAccount) ) {
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }
    private static void onAccountCreated(Account newAccount, Context context) {
        final int SYNC_INTERVAL = 60 * 60 * 24;
        final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
        TrackerSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);
        ContentResolver.setSyncAutomatically(newAccount,
                context.getString(R.string.content_authority), true);
        ContentResolver.addPeriodicSync(newAccount,
                context.getString(R.string.content_authority),
                Bundle.EMPTY,
                SYNC_INTERVAL);
        syncImmediately(context);
    }
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
// we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }
    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }

    //******************************* категории ******************************
    public void categorySyncServer(){
        String gToken = MonyeTrackerApplication.getGoogleToken(getContext());
        RestService restService = new RestService();
        SynchCategory synchCategory = restService.categorySynch(gToken,addCategorySyncServer());
        List<Data> dataListCategory = synchCategory.getData();
        if (synchCategory.getStatus().equalsIgnoreCase(ConstantString.SUCCESS)){
            Log.e(LOG_TAG, "Status: " + synchCategory.getStatus());
            List<Categories> categoriesNew = getDataList();
            for (Data data : dataListCategory){
                for (Categories categoriesList : categoriesNew){
                    if (data.getTitle().equals(categoriesList.name)){
                        categoriesList.idSync = data.getId();
                        break;
                    }
                }
            }
        }

        //Проверяем возвращаемый статус
        if (synchCategory.getStatus().equalsIgnoreCase(ConstantString.SUCCESS)){
            Log.e(LOG_TAG, "Status: " + synchCategory.getStatus());
        } else if (synchCategory.getStatus().equalsIgnoreCase(ConstantString.ERROR)){
            System.out.println(R.string.error_text);
        }
    }

    //Синхронизация категорий
    private List<Categories> getDataList()
    {
        return new Select()
                .from(Categories.class)
                .execute();
    }
    //Синхронизация с сервером
    public String addCategorySyncServer(){
        List<Categories> syncList =getDataList();
        ArrayList<String> data = new ArrayList<>();
        Data nData = new Data();
        // Конвертируем объекты
        Gson gson = new Gson();

        for (Categories itemList : syncList){
            nData.setTitle(itemList.toString());
            nData.setId(itemList.hashCode());
            data.add(gson.toJson(nData));
        }
        return data.toString();

    }

    //******************************* траты ******************************
    public void expensesSyncServer(){
        RestService restService = new RestService();
        String gToken = MonyeTrackerApplication.getGoogleToken(getContext());
        SynchExpense synchExpense = restService.expenseSynch(gToken, addExpensesSyncServer());
        if (synchExpense.getStatus().equalsIgnoreCase(ConstantString.SUCCESS)){
            Log.e(LOG_TAG, "Status: " + synchExpense.getStatus());
        } else if (synchExpense.getStatus().equalsIgnoreCase(ConstantString.ERROR)){
            System.out.println(R.string.error_text);
        }
    }
    private List<Expenses> getExpensesList()
    {
        return new Select()
                .from(Expenses.class)
                .execute();
    }

    public String addExpensesSyncServer(){
        List<Expenses> expensesList = getExpensesList();
        ArrayList<String> syncExpense = new ArrayList<>();
        CreateExpense nExpense = new CreateExpense();
        Gson gson = new Gson();

        for (Expenses expensesItem : expensesList){
            double priceValue = Double.parseDouble(expensesItem.price);
            nExpense.setId(0);
            nExpense.setCategoryId(expensesItem.category.idSync);
            nExpense.setComment(expensesItem.name);
            nExpense.setSum(priceValue);
            nExpense.setTrDate(gson.toJson(syncExpense));
        }
        return expensesList.toString();
    }


}
