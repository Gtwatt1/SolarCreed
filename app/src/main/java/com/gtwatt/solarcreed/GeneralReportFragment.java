package com.gtwatt.solarcreed;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gtwatt.solarcreed.model.Pen;
import com.gtwatt.solarcreed.model.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class GeneralReportFragment extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    ReportAdapter adapter;
    List<Report> reportList;
    FloatingActionButton fab;
    TextView emptyView;
    Spinner spinner;

    public GeneralReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reportList = new ArrayList<Report>();
        reportList = Report.listAll(Report.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.report_fragment, container, false);
        reportList = Report.listAll(Report.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.report_recycleview);
        spinner = (Spinner)view.findViewById(R.id.poultryPen);

        final List<Pen> pens = Pen.listAll(Pen.class);
        List<String> items =  new ArrayList<String>();


        for (Pen pen : pens){
            items.add(" " + pen.getPenNum() + "  " +pen.getName());
        }



        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);

        emptyView = (TextView)view.findViewById(R.id.emptyText);
        if (reportList.size() < 1){
            emptyView.setVisibility(View.VISIBLE);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "selected item", Toast.LENGTH_SHORT).show();
                Pen pen = pens.get(position);
                List<Report> reports = Report.find(Report.class, "penssid = ?", pen.getSSID());
                Toast.makeText(getContext(), String.valueOf(reports.size()), Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter( new ReportAdapter(getContext(), reports));
//                recyclerView.invalidate();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(items.size() <1){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
            builder1.setMessage("please Add A Pen before proceeding");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProfileFragment fragment = new ProfileFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });



            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        adapter = new ReportAdapter(getContext(), reportList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }



}
