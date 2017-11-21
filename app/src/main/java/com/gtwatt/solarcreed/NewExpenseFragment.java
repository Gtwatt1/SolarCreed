package com.gtwatt.solarcreed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Expense;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class NewExpenseFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_expense, container,false);
        final EditText miscExpense, birdExpense, feedExpense;
        TextView today = (TextView)view.findViewById(R.id.date_today);
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        today.setText(dateString);

        miscExpense = (EditText)view.findViewById(R.id.input_egg_expense);
        feedExpense = (EditText)view.findViewById(R.id.input_feed_expense);
        birdExpense = (EditText)view.findViewById(R.id.input_bird_expense);




        Expense expense = new DataBaseHandler(getContext()).getExpense(dateString);
        if(expense != null){
            miscExpense.setText(expense.getMiscExpense());
            feedExpense.setText(expense.getFeedExpense());
            birdExpense.setText(expense.getBirdExpense());
        }



        Button saveButton = (Button)view.findViewById(R.id.save_new_expense);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expense expense = new Expense(Integer.parseInt(miscExpense.getText().toString()),Integer.parseInt(feedExpense.getText().toString()), Integer.parseInt(birdExpense.getText().toString()));
                new DataBaseHandler(getContext()).addExpense(expense);
            }
        });

        return view;
    }
}
