package com.loftschool.moneytracker.ui.fragments;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.adapters.CategoryAdapter;
import com.loftschool.moneytracker.database.Expenses;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.category_fragment)
public class CategoryFragment extends Fragment {

@ViewById(R.id.context_recyclerview)
RecyclerView categoryRecycleView;

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Click(R.id.fab)
    void myButtonWasClicked() {
        Snackbar.make(getView(), "Nice, it's work !", Snackbar.LENGTH_SHORT).show();
        getActivity().setTitle("Категории");
    }

    @AfterViews
    void ready(){
       // List<Expenses> adapterData = getDataList();
     //   CategoryAdapter categoryAdapter = new CategoryAdapter(adapterData);

     //   categoryRecycleView.setAdapter(categoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycleView.setLayoutManager(linearLayoutManager);
        if (floatingActionButton.isPressed()){
            myButtonWasClicked();
        }

        getActivity().setTitle(getString(R.string.nav_drawer_categories));
    }

//    List<Expense> getDataList() {
//        List<Expense> data = new ArrayList<>();
//        long listDate = Calendar.getInstance().getTimeInMillis();
//        data.add(new Expense("Продукты",1000,new Date(listDate)));
//        data.add(new Expense("Услуги",2000,new Date(listDate)));
//        data.add(new Expense("Автомобиль",3000,new Date(listDate)));
//        return data;
//    }

}
