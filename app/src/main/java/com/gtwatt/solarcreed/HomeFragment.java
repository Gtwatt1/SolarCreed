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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/2/17.
 */

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter adapter;
    List<HomeItem> homeItems;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeItems = new ArrayList<HomeItem>();
        homeItems.add(new HomeItem("General Record", "bird", "Add Record", "5000"));
        homeItems.add(new HomeItem("Report", "egg", "View Report", "15000"));
        homeItems.add(new HomeItem("Vaccination", "feed", "Vaccination", "2000"));
        homeItems.add(new HomeItem("Power Unit", "power", "Buy Unit", "40000"));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.content_home, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.home_recycle_view);

        adapter = new HomeAdapter(getContext(), homeItems);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(2, 0, false, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;


    }
}
