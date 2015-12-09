package com.loftschool.moneytracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatisticsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.statistics_fragment, container, false);
        getActivity().setTitle(R.string.fragment_text_stat);
        return mainView;
    }


}
