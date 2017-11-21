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
import android.widget.Toast;

import com.gtwatt.solarcreed.model.Report;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class NewRecordFragment  extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_record, container,false);
        final EditText goodEgg,badEgg,usedFeed,newFeed,mortality,sickBird;
        TextView today = (TextView)view.findViewById(R.id.date_today);
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        today.setText(dateString);



        Report report = new DataBaseHandler(getContext()).getReport(dateString);

        goodEgg = (EditText)view.findViewById(R.id.input_new_good_egg);
        badEgg = (EditText)view.findViewById(R.id.input_new_damaged_egg);
        usedFeed = (EditText)view.findViewById(R.id.input_used_feed);

        newFeed = (EditText)view.findViewById(R.id.input_new_feed);
        mortality = (EditText)view.findViewById(R.id.input_mortality);
        sickBird = (EditText)view.findViewById(R.id.input_sick_bird);

        if(report != null){
            goodEgg.setText(report.getGoodEgg());
            badEgg.setText(report.getBadEgg());
            usedFeed.setText(report.getUsedFeed());
            newFeed.setText(report.getNewFeed());
            mortality.setText(report.getMortality());
            sickBird.setText(report.getSickBirds());
        }


        Button saveButton = (Button)view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report report = new Report(1,Integer.parseInt(goodEgg.getText().toString()),Integer.parseInt(badEgg.getText().toString()),Integer.parseInt(mortality.getText().toString()),Integer.parseInt(sickBird.getText().toString()),Integer.parseInt(usedFeed.getText().toString()),Integer.parseInt(newFeed.getText().toString()), 1);
                new DataBaseHandler(getContext()).addReport(report);
                Toast.makeText(getContext(),"Record Saved", Toast.LENGTH_SHORT);
            }
        });


        return view;
    }
}
