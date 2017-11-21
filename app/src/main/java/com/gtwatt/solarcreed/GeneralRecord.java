package com.gtwatt.solarcreed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Report;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GeneralRecord extends Fragment {

    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<RecordItem> recordItems;
    FloatingActionButton fab;

    public GeneralRecord() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        vaccines = new ArrayList<RecordItem>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String currentDate = sdf.format(new Date());
//        Report report = new DataBaseHandler(getContext()).getReport(currentDate);
//        vaccines.add(new RecordItem("Mortality","Total Number of ShowAlert that died today",report.getMortality()));
//        vaccines.add(new RecordItem("Sick ShowAlert","Total Number of Sick ShowAlert",report.getSickBirds()));
//
//        vaccines.add(new RecordItem("Damaged Eggs","Total number of damaged eggs collected today",report.getBadEgg()));
//        vaccines.add(new RecordItem("Good Eggs"," Total number of egg in good condiions",report.getGoodEgg()));
//
//        vaccines.add(new RecordItem("Used Bags","Total numbr of bags used total",report.getUsedFeed()));
//        vaccines.add(new RecordItem("New Feed","Total number of new feed",report.getNewFeed()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chicken, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.record_recycleview);


        TextView today = (TextView)view.findViewById(R.id.date_today);
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        today.setText(dateString);

        recordItems = new ArrayList<RecordItem>();
        Report report = new DataBaseHandler(getContext()).getReport(dateString);
        if(report != null){
            recordItems.add(new RecordItem("Mortality","Total Number of ShowAlert that died today",report.getMortality()));
            recordItems.add(new RecordItem("Sick ShowAlert","Total Number of Sick ShowAlert",report.getSickBirds()));

            recordItems.add(new RecordItem("Damaged Eggs","Total number of damaged eggs collected today",report.getBadEgg()));
            recordItems.add(new RecordItem("Good Eggs"," Total number of egg in good condiions",report.getGoodEgg()));

            recordItems.add(new RecordItem("Used Bags","Total numbr of bags used total",report.getUsedFeed()));
            recordItems.add(new RecordItem("New Feed","Total number of new feed",report.getNewFeed()));
        }else {
            recordItems.add(new RecordItem("Mortality", "Total Number of ShowAlert that died today", "0"));
            recordItems.add(new RecordItem("Sick ShowAlert", "Total Number of Sick ShowAlert", "0"));

            recordItems.add(new RecordItem("Damaged Eggs", "Total number of damaged eggs collected today", "0"));
            recordItems.add(new RecordItem("Good Eggs", " Total number of egg in good condiions", "0"));

            recordItems.add(new RecordItem("Used Bags", "Total numbr of bags used total", "0"));
            recordItems.add(new RecordItem("New Feed", "Total number of new feed", "0"));
        }

        fab = (FloatingActionButton)view.findViewById(R.id.chicken_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRecordFragment nextFrag= new NewRecordFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, nextFrag)

                        .addToBackStack(null)
                        .commit();
            }
        });

        adapter = new RecordAdapter(getContext(), recordItems, 0);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }
}