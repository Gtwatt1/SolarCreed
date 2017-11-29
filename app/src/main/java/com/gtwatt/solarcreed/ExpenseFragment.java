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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Expense;
import com.gtwatt.solarcreed.model.Pen;
import com.gtwatt.solarcreed.model.Report;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExpenseFragment extends Fragment {
    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<RecordItem> recordItems;
    FloatingActionButton fab;


    public ExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chicken, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.record_recycleview);


        Spinner spin = (Spinner)view.findViewById(R.id.poultryRecord);

        List<Pen> pens = Pen.listAll(Pen.class);
        List<String> items =  new ArrayList<String>();


        for (Pen pen : pens){
            items.add(" " + pen.getPenNum() + "  " +pen.getName());
        }



        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinadapter);

        TextView today = (TextView)view.findViewById(R.id.date_today);
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        today.setText(dateString);

        recordItems = new ArrayList<RecordItem>();
        List<Expense>  expenses = Expense.find(Expense.class, "date = ?", dateString);
        Expense expense = null;
        if(expenses.size() > 0){
            expense = expenses.get(0);
        }

        if(expense != null){

            recordItems.add(new RecordItem("Expense on Feed","Total amount spent on feeds  today",expense.getFeedExpense()));
            recordItems.add(new RecordItem("Expense on ShowAlert"," Total amount spent on birds ",expense.getBirdExpense()));
            recordItems.add(new RecordItem("Miscellanous Expense ","Miscellaneous",expense.getMiscExpense()));
            recordItems.add(new RecordItem("Total Expense  ","Total expense today",String.valueOf(Integer.parseInt(expense.getMiscExpense()) +Integer.parseInt(expense.getBirdExpense()) + Integer.parseInt(expense.getFeedExpense()))));
        }else {
            recordItems.add(new RecordItem("Expense on Feed","Total amount spent on feeds  today","0"));
            recordItems.add(new RecordItem("Expense on ShowAlert"," Total amount spent on birds ","0"));
            recordItems.add(new RecordItem("Expense on Egg ","Miscellaneous","0"));
            recordItems.add(new RecordItem("Total Expense  ","Total expense today","0"));

        }

        fab = (FloatingActionButton)view.findViewById(R.id.chicken_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewExpenseFragment nextFrag= new NewExpenseFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        adapter = new RecordAdapter(getContext(), recordItems,1);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridDecoration(1, 10, true, getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return  view;

    }

}