package com.loftschool.moneytracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.database.Categories;
import com.loftschool.moneytracker.database.Expenses;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CardViewHolder>{

    List<Categories> categories;

    public CategoryAdapter(List<Categories> categories ){
        this.categories = categories;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Categories category = categories.get(position);
        holder.nameText.setText(category.name);
//        holder.sumText.setText(category.price);
//        holder.dateName.setText(category.date);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView nameText;
//        protected  TextView sumText;
//        protected  TextView dateName;

        public CardViewHolder(View convertView){
            super(convertView);
            nameText = (TextView) convertView.findViewById(R.id.name_text);
//            1
        }

    }

}
