package com.gtwatt.solarcreed;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gtwatt.solarcreed.model.Pen;
import com.gtwatt.solarcreed.model.Report;
import com.gtwatt.solarcreed.model.Vaccine;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        final EditText goodEgg,badEgg,usedFeed,newFeed,mortality,sickBird, culledbird;
        TextView today = (TextView)view.findViewById(R.id.date_today);
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        final Spinner spinner = (Spinner)view.findViewById(R.id.addRecordspinner);
        today.setText(dateString);


        List<Report> reports = Report.find(Report.class, "date = ?", dateString);
        Report report = null;
        if(reports.size() > 0){
            report = reports.get(0);
        }

        final List<Pen> pens = Pen.listAll(Pen.class);
        List<String> items =  new ArrayList<String>();


        for (Pen pen : pens){
            items.add(" " + pen.getPenNum() + "  " +pen.getName());
        }



        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);

        goodEgg = (EditText)view.findViewById(R.id.input_new_good_egg);
        badEgg = (EditText)view.findViewById(R.id.input_new_damaged_egg);
        usedFeed = (EditText)view.findViewById(R.id.input_used_feed);

        newFeed = (EditText)view.findViewById(R.id.input_new_feed);
        mortality = (EditText)view.findViewById(R.id.input_mortality);
        sickBird = (EditText)view.findViewById(R.id.input_sick_bird);
        culledbird = (EditText)view.findViewById(R.id.input_new_culling);


        if(report != null){
            goodEgg.setText(report.getGoodEgg());
            badEgg.setText(report.getBadEgg());
            usedFeed.setText(report.getUsedFeed());
            newFeed.setText(report.getNewFeed());
            mortality.setText(report.getMortality());
            sickBird.setText(report.getSickBirds());
            culledbird.setText(report.getCulling());
        }


        Button saveButton = (Button)view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report report = new Report(1,Integer.parseInt(goodEgg.getText().toString()),Integer.parseInt(badEgg.getText().toString()),
                        Integer.parseInt(mortality.getText().toString()),Integer.parseInt(sickBird.getText().toString()),
                        Integer.parseInt(usedFeed.getText().toString()),Integer.parseInt(newFeed.getText().toString()),
                        1, pens.get(spinner.getSelectedItemPosition()).getSSID(), Integer.parseInt(culledbird.getText().toString()));
                String dtStart = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
                List<Report> reports = Report.find(Report.class, "date = ?", dtStart);
                if(reports.size() > 0){
                    report.update();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Record updated");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getFragmentManager().popBackStackImmediate();
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else {
                    report.save();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Record Saved");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getFragmentManager().popBackStackImmediate();
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                Toast.makeText(getContext(),"Record Saved", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
