package com.loftschool.moneytracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;


@EFragment (R.layout.statistics_fragment)
public class StatisticsFragment extends Fragment {
/*Вывести нужную страницу при выборе ее в навигационном меню. При выборе
“Категории” вывести список с категориями, как в дизайне приложения, при выборе “Статистика” и “Настройки” вывести текст “Здесь будет статистика” и “Здесь будут настройки”.
 */
    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.fragment_text_stat);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View mainView = inflater.inflate(R.layout.statistics_fragment, container, false);
//        getActivity().setTitle(R.string.fragment_text_stat);
//        return mainView;
//
//    }


}
