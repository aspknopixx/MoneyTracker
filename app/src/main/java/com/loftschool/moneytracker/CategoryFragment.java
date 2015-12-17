package com.loftschool.moneytracker;


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

@EFragment(R.layout.category_fragment)
public class CategoryFragment extends Fragment {
/*Вывести нужную страницу при выборе ее в навигационном меню. При выборе
“Категории” вывести список с категориями, как в дизайне приложения, при выборе “Статистика” и “Настройки” вывести текст “Здесь будет статистика” и “Здесь будут настройки”.
 */
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View mainView = inflater.inflate(R.layout.category_fragment, container, false);
//        RecyclerView expenseRecyclerView = (RecyclerView) mainView.findViewById(R.id.context_recyclerview);
//        List<Expense> adapterData = getDataList();
//        CategoryAdapter categoryAdapter = new CategoryAdapter(adapterData);
//        expenseRecyclerView.setAdapter(categoryAdapter);
//
//        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity());
//        linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        expenseRecyclerView.setLayoutManager(linerLayoutManager);
//
//        FloatingActionButton floatingActionButton = (FloatingActionButton) mainView.findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(mainView, "Nice, it's work !",Snackbar.LENGTH_SHORT).show();
//            }
//        });
//
//        getActivity().setTitle("Категории");
//        return mainView;
//    }
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
        List<Expense> adapterData = getDataList();
        CategoryAdapter categoryAdapter = new CategoryAdapter(adapterData);

        categoryRecycleView.setAdapter(categoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycleView.setLayoutManager(linearLayoutManager);
        if (floatingActionButton.isPressed()){
            myButtonWasClicked();
        }

        getActivity().setTitle(getString(R.string.nav_drawer_categories));
    }

    List<Expense> getDataList() {
        List<Expense> data = new ArrayList<>();
        long listDate = Calendar.getInstance().getTimeInMillis();
        data.add(new Expense("Продукты",1000,new Date(listDate)));
        data.add(new Expense("Услуги",2000,new Date(listDate)));
        data.add(new Expense("Автомобиль",3000,new Date(listDate)));
        return data;
    }

}
