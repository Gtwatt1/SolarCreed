package com.gtwatt.solarcreed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

        recordItems = new ArrayList<RecordItem>();
        recordItems.add(new RecordItem("Mortality","Total Number of Birds that died today","4"));
        recordItems.add(new RecordItem("Sick Birds","Total Number of Sick Birds","4"));

        recordItems.add(new RecordItem("Damaged Eggs","Total number of damaged eggs collected today","4"));
        recordItems.add(new RecordItem("Good Eggs"," Total number of egg in good condiions","4"));

        recordItems.add(new RecordItem("Used Bags","Total numbr of bags used total","4"));
        recordItems.add(new RecordItem("New Feed","Total number of new feed","4"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chicken, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.record_recycleview);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddBird nextFrag= new AddBird();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame, nextFrag)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

        adapter = new RecordAdapter(getContext(), recordItems, 0);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }
}