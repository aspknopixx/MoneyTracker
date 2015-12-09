package com.loftschool.moneytracker;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import  static android.graphics.Color.rgb;

import java.util.List;
import java.util.Random;

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    List<Expense> expenses;
    static int backGroundColor;
    public ExpenseAdapter(Context context,  List<Expense> expenses) {
        super(context, 0, expenses);
        this.expenses = expenses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Expense expense = getItem(position);
        Random random = new Random();

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.lItem);
        backGroundColor = Color.argb(50, random.nextInt(256), random.nextInt(250),random.nextInt(256));
        relativeLayout.setBackgroundColor(backGroundColor);

        TextView name = (TextView) convertView.findViewById(R.id.name_text);
        TextView sum = (TextView) convertView.findViewById(R.id.sum_text);
        TextView date = (TextView) convertView.findViewById(R.id.date_text);

        name.setText(expense.getTitle());
        sum.setText(expense.getSum());
        date.setText(expense.getDate());

        return convertView;
    }
}



