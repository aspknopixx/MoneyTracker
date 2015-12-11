package com.loftschool.moneytracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CardViewHolder>{

    List<Expense> expenses;

    public CategoryAdapter(List<Expense> expenses){
        this.expenses = expenses;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.nameText.setText(expense.getTitle());
        holder.sumText.setText(expense.getSum());
        holder.dateName.setText(expense.getDate());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView nameText;
        protected  TextView sumText;
        protected  TextView dateName;

        public CardViewHolder(View convertView){
            super(convertView);
            nameText = (TextView) convertView.findViewById(R.id.name_text);
            sumText = (TextView) convertView.findViewById(R.id.sum_text);
            dateName = (TextView) convertView.findViewById(R.id.date_text);
        }

    }

}