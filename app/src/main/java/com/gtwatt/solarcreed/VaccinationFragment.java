package com.gtwatt.solarcreed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


public class VaccinationFragment extends Fragment {


    RecyclerView recyclerView;
    VaccinationAdapter adapter;
    List<RecordItem> recordItems;
    FloatingActionButton fab;

    public VaccinationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recordItems = new ArrayList<RecordItem>();
        recordItems.add(new RecordItem("Mortality","Total Number of Birds that died today","11/12/2017"));
        recordItems.add(new RecordItem("Sick Birds","Total Number of Sick Birds","11/12/2017"));
        recordItems.add(new RecordItem("Current Stock","Total Number of Birds Today","11/12/2017"));
        recordItems.add(new RecordItem("Today's Expenses","Total amount of  money spent today","11/12/2017"));
        recordItems.add(new RecordItem("Total Expense","Total amount of spent ","11/12/2017"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vaccination, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.vaccination_recycle_view);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VaccinationReminder nextFrag= new VaccinationReminder();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });




        adapter = new VaccinationAdapter(getContext(), recordItems);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }

}
