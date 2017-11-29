package com.gtwatt.solarcreed;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gtwatt.solarcreed.model.Pen;
import com.gtwatt.solarcreed.model.Report;
import com.gtwatt.solarcreed.model.Vaccine;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VaccinationReminder extends Fragment {

     int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_vaccination_reminder, container, false);
        final EditText vaccineType, numberBirds, vaccineDetails,vaccineDate, vaccineTime;
        Spinner spin;


        vaccineDate = (EditText)view.findViewById(R.id.input_vaccine_select_date);
        numberBirds = (EditText)view.findViewById(R.id.input_number_chicken);
        vaccineDetails = (EditText)view.findViewById(R.id.input_vaccination_details);
        vaccineType = (EditText)view.findViewById(R.id.input_vaccination_type);
        Button saveButton = (Button)view.findViewById(R.id.save_vaccine);
        vaccineTime = (EditText)view.findViewById(R.id.input_vaccine_select_time) ;
        spin = (Spinner)view.findViewById(R.id.poultryRecord);

        List<Pen> pens = Pen.listAll(Pen.class);
        List<String> items =  new ArrayList<String>();


        for (Pen pen : pens){
            items.add(" " + pen.getPenNum() + "  " +pen.getName());
        }

        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinadapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(vaccineType.getText().toString().isEmpty()) && !(numberBirds.getText().toString().isEmpty()) && !(vaccineDetails.getText().toString().isEmpty()) && !(vaccineDate.getText().toString().isEmpty()) && !(vaccineTime.getText().toString().isEmpty())) {
                    String date = "";
                    String dtStart = vaccineDate.getText().toString() + " " + vaccineTime.getText().toString();

                    Vaccine vaccine = new Vaccine(vaccineType.getText().toString(), Integer.parseInt(numberBirds.getText().toString()), dtStart, vaccineDetails.getText().toString());

                        vaccine.save();
                }else{
                    Toast.makeText(getContext(), "Please Fill in the details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vaccineTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                vaccineTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();

            }
        });

        vaccineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Calendar c = Calendar.getInstance();
                                c.set(year, monthOfYear , dayOfMonth, 0, 0);
                                String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime());

                                vaccineDate.setText(dateString);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        return  view;
    }


    void setAlarm(Calendar setcalender){
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");
        notificationIntent.putExtra("EverydayAlarm", 2);



        PendingIntent broadcast = PendingIntent.getBroadcast(getContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setcalendar = Calendar.getInstance();

        setcalendar.set(Calendar.HOUR_OF_DAY, 16);
        setcalendar.set(Calendar.MINUTE, 0);
        setcalendar.set(Calendar.SECOND, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES,broadcast);
        Toast.makeText(getContext(),"send", Toast.LENGTH_SHORT).show();

    }
}
