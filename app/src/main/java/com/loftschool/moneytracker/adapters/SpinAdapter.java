package com.loftschool.moneytracker.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.database.Categories;

import java.util.List;

public class SpinAdapter extends ArrayAdapter<Categories> implements SpinnerAdapter {

    List<Categories> categories;

    public SpinAdapter(Context ctx, List<Categories> listCategory){
        super(ctx,0,listCategory);
        this.categories = listCategory;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Categories category = getItem(position);

        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_txt, parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.spinner_txt_view);

        name.setText(category.name);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Categories category = getItem(position);

        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_txt, parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.spinner_txt_view);

        name.setText(category.name);

        return convertView;
    }
}
