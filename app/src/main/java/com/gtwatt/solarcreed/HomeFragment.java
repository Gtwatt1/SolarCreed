package com.gtwatt.solarcreed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/2/17.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {


    PrefManager prefManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.content_home, container, false);

        Button addFirst,addSecond, addThird, addFourth;
        addFirst = (Button)view.findViewById(R.id.first_add);
        addSecond = (Button)view.findViewById(R.id.second_add);
        addThird = (Button)view.findViewById(R.id.third_add);
        addFourth = (Button)view.findViewById(R.id.fourth_add);

        addFirst.setOnClickListener(this);
        addSecond.setOnClickListener(this);
        addThird.setOnClickListener(this);
        addFourth.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.first_add:
                NewRecordFragment firstFrag= new NewRecordFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, firstFrag)

                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.second_add:
                ReportFragment secondFrag= new ReportFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, secondFrag)

                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.third_add:
                VaccinationReminder thirdFrag= new VaccinationReminder();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, thirdFrag)

                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.fourth_add:
//                NewRecordFragment fourthFrag= new NewRecordFragment();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame, fourthFrag)
//
//                        .addToBackStack(null)
//                        .commit();
                break;
        }

    }
}
