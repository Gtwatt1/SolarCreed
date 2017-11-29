package com.gtwatt.solarcreed;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Pen;
import com.gtwatt.solarcreed.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/21/17.
 */

public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ProfilePenAdapter adapter;
    FloatingActionButton fab;
    List<Pen> pens;
    TextView emptyText;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pens = new ArrayList<>();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.profile_frag, container, false);

        pens = Pen.listAll(Pen.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.record_recycleview);
        fab = (FloatingActionButton)view.findViewById(R.id.pen_add_fab);
        emptyText = (TextView)view.findViewById(R.id.emptyText);
        if(pens.size() > 0){
            emptyText.setVisibility(View.GONE);
        }else{
            emptyText.setVisibility(View.VISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Pen pen = ShowAlert.addPenDialog(getContext(), null);
//               pens.add(pen);
//               adapter.notifyDataSetChanged();
            }
        });

        adapter = new ProfilePenAdapter(getContext(), pens);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        pens = Pen.listAll(Pen.class);
        adapter.notifyDataSetChanged();
    }
}
