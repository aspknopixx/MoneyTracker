package com.loftschool.moneytracker.sync;


import android.util.Log;

import com.activeandroid.query.Select;
import com.google.gson.Gson;
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

public class DBtoServerSync {

    private static final String LOG_TAG = TrackerSyncAdapter.class.getSimpleName();

//******************************* категории ******************************
    public void categorySyncServer(){
        RestService restService = new RestService();
        SynchCategory synchCategory = restService.categorySynch(addCategorySyncServer());
        List<Data> dataListCategory = synchCategory.getData();
        if (synchCategory.getStatus().equalsIgnoreCase(ConstantString.SUCCESS)){
            Log.d(LOG_TAG, "Status: " + synchCategory.getStatus());
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
           Log.d(LOG_TAG, "Status: " + synchCategory.getStatus());
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
        SynchExpense synchExpense = restService.expenseSynch(addExpensesSyncServer());
        if (synchExpense.getStatus().equalsIgnoreCase(ConstantString.SUCCESS)){
            Log.d(LOG_TAG, "Status: " + synchExpense.getStatus());
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
            int categoryId = Integer.parseInt(expensesItem.category.getId().toString());
            nExpense.setId(0);
            nExpense.setCategoryId(categoryId);
            nExpense.setComment(expensesItem.name);
            nExpense.setSum(priceValue);
            nExpense.setTrDate(gson.toJson(syncExpense));
        }
        return expensesList.toString();
    }
}
