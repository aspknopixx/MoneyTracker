package com.loftschool.moneytracker.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.loftschool.moneytracker.adapters.ExpenseAdapter;
import com.loftschool.moneytracker.database.Categories;
import com.loftschool.moneytracker.database.Expenses;
import com.loftschool.moneytracker.ui.activities.AddExpenseActivity_;
import com.loftschool.moneytracker.R;
import android.support.v4.app.LoaderManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
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
//        List<Expense> adapterData = getDataList();
//        ExpenseAdapter expenseAdapter = new ExpenseAdapter(adapterData);
//        expenseRecyclerView.setAdapter(expenseAdapter);

        Categories categoryFun = new Categories("Fun");
        categoryFun.save();
        Expenses expenses = new Expenses("321","cinema","15.12.15",categoryFun);
        expenses.save();

        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity());
        linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseRecyclerView.setLayoutManager(linerLayoutManager);
        if (floatingActionButton.isPressed()){
            ButtonWasClicked();
            getActivity().setTitle(getString(R.string.nav_drawer_expenses));
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Expenses>>() {
            @Override
            public Loader<List<Expenses>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Expenses>> loader = new AsyncTaskLoader<List<Expenses>>(getActivity()) {
                    @Override
                    public List<Expenses> loadInBackground() {
                        return getDataList();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<Expenses>> loader, List<Expenses> data) {
                expenseRecyclerView.setAdapter(new ExpenseAdapter(data));
            }

            @Override
            public void onLoaderReset(Loader<List<Expenses>> loader) {

            }
        });
    }

    private List<Expenses> getDataList(){
      return new Select()
              .from(Expenses.class)
              .execute();
    }


}
