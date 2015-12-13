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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpensesFragment extends Fragment{
/*Вывести нужную страницу при выборе ее в навигационном меню. При выборе
“Категории” вывести список с категориями, как в дизайне приложения, при выборе “Статистика” и “Настройки” вывести текст “Здесь будет статистика” и “Здесь будут настройки”.
 */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mainView = inflater.inflate(R.layout.expenses_fragment, container, false);
        RecyclerView expenseRecyclerView = (RecyclerView) mainView.findViewById(R.id.context_recyclerview);
        List<Expense> adapterData = getDataList();
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(adapterData);
        expenseRecyclerView.setAdapter(expenseAdapter);

        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity());
        linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseRecyclerView.setLayoutManager(linerLayoutManager);

        FloatingActionButton floatingActionButton = (FloatingActionButton) mainView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Snackbar.make(mainView, "Nice, it's work !",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddExpenseActivity.class);
                intent.putExtra("key","value");
                getActivity().startActivity(intent);
            }
        });

        getActivity().setTitle("Траты");
        return mainView;
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
