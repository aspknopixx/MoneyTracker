package com.loftschool.moneytracker;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import  static android.graphics.Color.rgb;

//раскрашиваем строчки с помощью backgroundColor

import java.util.List;
import java.util.Random;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.CardViewHolder> {
    List<Expense> expenses;

    public ExpenseAdapter(List<Expense> expenses){
        this.expenses = expenses;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View  convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CardViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.name_text.setText(expense.getTitle());
        holder.sum_text.setText(expense.getSum());
        holder.date_name.setText(expense.getDate());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder{
    protected  TextView name_text;
    protected  TextView sum_text;
    protected  TextView date_name;

    public CardViewHolder(View convertView){
        super(convertView);
        name_text = (TextView) convertView.findViewById(R.id.name_text);
        sum_text = (TextView) convertView.findViewById(R.id.sum_text);
        date_name = (TextView) convertView.findViewById(R.id.date_text);
    }

    }
}



