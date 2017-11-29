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
import com.gtwatt.solarcreed.model.Vaccine;

import java.util.ArrayList;
import java.util.List;


public class VaccinationFragment extends Fragment {


    RecyclerView recyclerView;
    VaccinationAdapter adapter;
    List<Vaccine> vaccines;
    FloatingActionButton fab;
    TextView emptyView;

    public VaccinationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vaccines = new ArrayList<Vaccine>();
//        vaccines = new DataBaseHandler(getContext()).getAllVaccine();
//        vaccines.add(new Vaccine("gsbs",3,"olas", "jokes"));


    }


    @Override
    public void onResume() {
        super.onResume();
        vaccines = Vaccine.listAll(Vaccine.class);
        adapter.setAdapterData(vaccines);
        if (vaccines.size() < 1){
            emptyView.setVisibility(View.VISIBLE);
        }else{
            emptyView.setVisibility(View.GONE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vaccination, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.vaccination_recycle_view);
        emptyView = (TextView) view.findViewById(R.id.emptyText);
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




        adapter = new VaccinationAdapter(getContext(), vaccines);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }


}
