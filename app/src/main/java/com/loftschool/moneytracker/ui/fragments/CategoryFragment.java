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
import com.activeandroid.query.Select;
import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.adapters.CategoryAdapter;
import com.loftschool.moneytracker.database.Categories;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

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

    private void loadData(){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Categories>>() {
            @Override
            public Loader<List<Categories>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Categories>> loader = new AsyncTaskLoader<List<Categories>>(getActivity()) {
                    @Override
                    public List<Categories> loadInBackground() {
                        return getDataList();
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




    private List<Categories> getDataList()
    {
        return new Select()
                .from(Categories.class)
                .execute();
    }

}
