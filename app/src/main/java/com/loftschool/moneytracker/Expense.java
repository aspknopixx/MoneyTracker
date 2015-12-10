package com.loftschool.moneytracker;


import java.text.SimpleDateFormat;
import java.util.Date;

// изменил string sum на int sum

public class Expense {
    private String title;
    private int sum;
    private Date date;

    public Expense(String title, int sum, Date date){
        this.title = title;
        this.sum = sum;
        this.date = date;
    }


    public String getTitle(){
        return title;
    }

    public String getSum(){
        return Integer.toString(sum);
    }

    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return  dateFormat.format(date);
    }

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public void setSum(int newSum){
        sum = newSum;
    }

    public void setDate(Date newDate){
        date = newDate;
    }
}

