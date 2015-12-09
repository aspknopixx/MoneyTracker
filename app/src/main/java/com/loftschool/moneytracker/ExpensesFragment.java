package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpensesFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.expenses_fragment, container, false);
        ListView expenseListView = (ListView) mainView.findViewById(R.id.list_view);
        List<Expense> adapterData = getDataList();
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(getActivity(), adapterData);
        expenseListView.setAdapter(expenseAdapter);
        getActivity().setTitle("Траты");
        return mainView;
    }

    private List<Expense> getDataList(){
        List<Expense> data = new ArrayList<>();
        long ListDate = Calendar.getInstance().getTimeInMillis();
        data.add(new Expense("Телефон",1000,new Date(ListDate)));
        data.add(new Expense("Щетка",2000,new Date(ListDate)));
        data.add(new Expense("Продукты",3000,new Date(ListDate)));
        data.add(new Expense("Интернет",4000,new Date(ListDate)));
        return data;
    }
}
