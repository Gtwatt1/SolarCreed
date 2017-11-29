package com.gtwatt.solarcreed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/6/17.
 */

public class ExpenseReportFragment extends Fragment {
    RecyclerView recyclerView;
    ExpenseFragmentAdapter adapter;
    List<Expense> expenses;
    TextView emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.report_fragment, container, false);
        emptyView = (TextView)view.findViewById(R.id.emptyText);
        expenses = Expense.listAll(Expense.class);

        if (expenses.size() < 1){
            emptyView.setVisibility(View.VISIBLE);
        }else{
            emptyView.setVisibility(View.GONE);
        }



            recyclerView = (RecyclerView) view.findViewById(R.id.report_recycleview);

            adapter = new ExpenseFragmentAdapter(getContext(), expenses);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            return view;

    }
}
