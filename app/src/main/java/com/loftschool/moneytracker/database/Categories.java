package com.loftschool.moneytracker.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.loftschool.moneytracker.util.ConstantString;

import java.util.List;

@Table(name = ConstantString.CATEGORIES)
public class Categories extends Model {

    @Column(name = ConstantString.NAME)
    public String name;

    @Column(name = "idSync")
    public int idSync;

    public Categories() {
        super();
    }

    public Categories(String name, int idSync) {
        super();
        this.name = name;
        this.idSync = idSync;
    }

    public List<Expenses> expenses() {
        return getMany(Expenses.class, ConstantString.CATEGORY);
    }

    @Override
    public String toString() {
        return name;
    }
}
