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


public class FeedsFragment extends Fragment {
    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<RecordItem> recordItems;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recordItems = new ArrayList<RecordItem>();
        recordItems.add(new RecordItem("Used Bags","Total numbr of bags used total","4"));
        recordItems.add(new RecordItem("New Feed","Total number of new feed","4"));
        recordItems.add(new RecordItem("Current Stock","Total number of stock","4"));
        recordItems.add(new RecordItem("Today Expense","Amount spent today","4"));
        recordItems.add(new RecordItem("Total Expense","Total amount spent ","4"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chicken, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.record_recycleview);



        adapter = new RecordAdapter(getContext(), recordItems, 1);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }

}
