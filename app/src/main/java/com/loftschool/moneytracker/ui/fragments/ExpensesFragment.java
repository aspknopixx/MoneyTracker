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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.util.List;

@EFragment(R.layout.expenses_fragment)
@OptionsMenu(R.menu.search_menu)
public class ExpensesFragment extends Fragment{

    private static final String FILTER_ID = "filter_id";


    @ViewById(R.id.context_recyclerview)
    RecyclerView expenseRecyclerView;

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @OptionsMenuItem(R.id.search_action)
    MenuItem menuItem;

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

//        Categories categoryFun = new Categories("Fun");
//        categoryFun.save();
//        Expenses expenses = new Expenses("321","cinema","15.12.15",categoryFun);
//        expenses.save();

        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(getActivity());
        linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expenseRecyclerView.setLayoutManager(linerLayoutManager);
        if (floatingActionButton.isPressed()){
            ButtonWasClicked();
        }
        getActivity().setTitle(getString(R.string.nav_drawer_expenses));

    }


    @Override
    public void onResume() {
        super.onResume();
        loadData("");
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_title));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("LOG_TAG", "Full Query:" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("LOG_TAG", "Current text: " + newText);
                BackgroundExecutor.cancelAll(FILTER_ID, true);
                delayedQuery(newText);
                return false;
            }
        });
    }

    @Background(delay = 800, id = FILTER_ID)
    public void delayedQuery(String filter) {
        loadData(filter);
    }

    private void loadData(final String filter){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Expenses>>() {
            @Override
            public Loader<List<Expenses>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Expenses>> loader = new AsyncTaskLoader<List<Expenses>>(getActivity()) {
                    @Override
                    public List<Expenses> loadInBackground() {
                        return getDataList(filter);
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

    private List<Expenses> getDataList(String filter){
      return new Select()
              .from(Expenses.class)
              .where("Name LIKE ?", new String[] {'%' + filter + '%'})
              .execute();
    }


}
