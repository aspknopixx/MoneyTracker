package com.loftschool.moneytracker.ui.fragments;




import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.activeandroid.query.Select;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.adapters.CategoryAdapter;
import com.loftschool.moneytracker.database.Categories;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.util.List;

@EFragment(R.layout.category_fragment)
@OptionsMenu(R.menu.search_menu)
public class CategoryFragment extends Fragment {

    private final static String FILTER_ID = "filter_id";

@ViewById(R.id.context_recyclerview)
RecyclerView categoryRecycleView;

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @OptionsMenuItem(R.id.search_action)
    MenuItem menuItem;

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
                Log.e("LOG_TAG", "Full query categories" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("LOG_TAG", "Current text categories" + newText);
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


    //    List<Expense> getDataList() {
//        List<Expense> data = new ArrayList<>();
//        long listDate = Calendar.getInstance().getTimeInMillis();
//        data.add(new Expense("Продукты",1000,new Date(listDate)));
//        data.add(new Expense("Услуги",2000,new Date(listDate)));
//        data.add(new Expense("Автомобиль",3000,new Date(listDate)));
//        return data;
//    }

    private void loadData(final String filter){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Categories>>() {
            @Override
            public Loader<List<Categories>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Categories>> loader = new AsyncTaskLoader<List<Categories>>(getActivity()) {
                    @Override
                    public List<Categories> loadInBackground() {
                        return getDataList(filter);
                    }
                };
                loader.forceLoad();
                return loader;
            }




            @Override
            public void onLoadFinished(Loader<List<Categories>> loader, List<Categories> data) {
                categoryRecycleView.setAdapter(new CategoryAdapter(data));
            }

            @Override
            public void onLoaderReset(Loader<List<Categories>> loader) {

            }
        });


    }


    private List<Categories> getDataList(String filter){
        return new Select()
                .from(Categories.class)
                .where("Name LIKE ?", new String[]{'%' + filter + '%'})
                .execute();
    }


}
