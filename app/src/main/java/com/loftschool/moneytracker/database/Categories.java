package com.loftschool.moneytracker.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.loftschool.moneytracker.util.ConstantString;

import java.util.List;

@Table(name = ConstantString.categories)
public class Categories extends Model{

    @Column(name = ConstantString.name)
    public String name;

    public Categories(){
        super();
    }

    public Categories(String name){
        super();
        this.name = name;
    }

    public List<Expenses> expenses(){
        return getMany(Expenses.class, ConstantString.category);
    }

}
