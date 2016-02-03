package com.loftschool.moneytracker.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.loftschool.moneytracker.util.ConstantString;


@Table(name = ConstantString.EXPENSES)
public class Expenses extends Model{

    @Column(name = ConstantString.PRICE)
    public String price;

    @Column(name = ConstantString.NAME)
    public String name;

    @Column(name = ConstantString.DATE)
    public String date;

    @Column(name = ConstantString.CATEGORY)
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
