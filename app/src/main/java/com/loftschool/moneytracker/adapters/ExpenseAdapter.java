package com.loftschool.moneytracker.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loftschool.moneytracker.R;
import com.loftschool.moneytracker.database.Expenses;

//раскрашиваем строчки с помощью backgroundColor

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.CardViewHolder> {
    List<Expenses> expenses;

    public ExpenseAdapter(List<Expenses> expenses){
        this.expenses = expenses;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View  convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Expenses expense = expenses.get(position);
        holder.nameText.setText(expense.name);
        holder.sumText.setText(expense.price);
        holder.dateName.setText(expense.date);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder{
    protected  TextView nameText;
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



