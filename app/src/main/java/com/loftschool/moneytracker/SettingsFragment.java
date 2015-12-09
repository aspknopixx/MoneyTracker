package com.loftschool.moneytracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SettingsFragment extends Fragment {
/*Вывести нужную страницу при выборе ее в навигационном меню. При выборе
“Категории” вывести список с категориями, как в дизайне приложения, при выборе “Статистика” и “Настройки” вывести текст “Здесь будет статистика” и “Здесь будут настройки”.
 */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.settings_fragment, container, false);
        getActivity().setTitle(R.string.fragment_text_setting);
        return mainView;

    }
}