package com.loftschool.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.expenses_fragment)
public class ExpensesFragment extends Fragment{
/*Вывести нужную страницу при выборе ее в навигационном меню. При выборе
“Категории” вывести список с категориями, как в дизайне приложения, при выборе “Статистика” и “Настройки” вывести текст “Здесь будет статистика” и “Здесь будут настройки”.
 */

    @ViewById(R.id.context_recyclerview)
    RecyclerView expenseRecyclerView;

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Click(R.id.fab)
    void ButtonWasClicked(){
        Intent intent = new Intent(getActivity(), AddExpenseActivity_.class);
        getActivity().startActivity(intent);

    }

    @AfterViews
    void ready(){
        List<Expense> adapterData = getDataList();
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(adapterData);
        expenseRecyclerView.setAdapter(expenseAdapter);

        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity());
       linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseRecyclerView.setLayoutManager(linerLayoutManager);
        if (floatingActionButton.isPressed()){
            ButtonWasClicked();
            getActivity().setTitle(getString(R.string.nav_drawer_expenses));
        }

    }



    private List<Expense> getDataList(){
        List<Expense> data = new ArrayList<>();
        long listDate = Calendar.getInstance().getTimeInMillis();
        data.add(new Expense("Телефон",1000,new Date(listDate)));
        data.add(new Expense("Щетка",2000,new Date(listDate)));
        data.add(new Expense("Продукты",3000,new Date(listDate)));
        data.add(new Expense("Интернет",4000,new Date(listDate)));
        return data;
    }
}
