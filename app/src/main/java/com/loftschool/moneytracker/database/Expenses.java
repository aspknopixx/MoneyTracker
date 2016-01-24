package com.loftschool.moneytracker.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.loftschool.moneytracker.util.ConstantString;


@Table(name = ConstantString.expenses)
public class Expenses extends Model{

    @Column(name = ConstantString.price)
    public String price;

    @Column(name = ConstantString.name)
    public String name;

    @Column(name = ConstantString.date)
    public String date;

    @Column(name = ConstantString.category)
    public Categories category;

    public Expenses(){
        super();
    }

    public Expenses(String price, String name, String date, Categories category){
        super();
        this.price = price;
        this.name = name;
        this.date = date;
        this.category = category;

    }
}
