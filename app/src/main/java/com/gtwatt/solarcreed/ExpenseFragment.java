package com.gtwatt.solarcreed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtwatt.solarcreed.R;

import java.util.ArrayList;
import java.util.List;


public class ExpenseFragment extends Fragment {
    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<RecordItem> recordItems;

    public ExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recordItems = new ArrayList<RecordItem>();
//        reportList.add(new RecordItem("Collection","Total number of eggs collected today","4"));
//        reportList.add(new RecordItem("Damage Eggs","Total number of damaged eggs collected today","4"));
//        reportList.add(new RecordItem("Current Stock"," Total number of eggs","4"));
//        reportList.add(new RecordItem("Today Expense"," amount of money spent today","4"));
//        reportList.add(new RecordItem("Total Expense","Total amount of money spent","4"));


        recordItems.add(new RecordItem("Expense on Feed","Total amount spent on feeds  today","4"));
        recordItems.add(new RecordItem("Expense on Birds"," Total amount spent on birds ","4"));
        recordItems.add(new RecordItem("Total expense ","Total expense today","4"));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chicken, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.record_recycleview);



        adapter = new RecordAdapter(getContext(), recordItems,1);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }

}