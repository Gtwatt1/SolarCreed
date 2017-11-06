package com.gtwatt.solarcreed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class ReportFragment extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    ReportAdapter adapter;
    List<Report> reportList;
    FloatingActionButton fab;

    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reportList = new ArrayList<Report>();
        reportList.add(new Report(1,300,50,2,12,30,20));
        reportList.add(new Report(1,300,50,2,12,30,20));
        reportList.add(new Report(1,300,50,2,12,30,20));
        reportList.add(new Report(1,300,50,2,12,30,20));
        reportList.add(new Report(1,300,50,2,12,30,20));
        reportList.add(new Report(1,300,50,2,12,30,20));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.report_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.report_recycleview);

        adapter = new ReportAdapter(getContext(), reportList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }
}
