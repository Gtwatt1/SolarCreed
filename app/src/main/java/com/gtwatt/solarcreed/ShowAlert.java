package com.gtwatt.solarcreed;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gtwatt.solarcreed.model.Pen;

import org.greenrobot.greendao.annotation.Entity;

/**
 * Created by Gtwatt on 11/4/17.
 */
@Entity
public class ShowAlert {
    static Pen savedPen;


    private Long id;
    private String ij;


    public  static void  showAlert(Context context, String message){



        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


    public static Pen addPenDialog(final Context mcontext, final Pen pen ){
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mcontext);
        final AlertDialog alertDialog = builder.create();
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View mView = inflater.inflate(R.layout.add_prof_pen_dialog, null);
        final EditText penNumber, penName, birdNumber, supervisorName;
        penNumber = (EditText)mView.findViewById(R.id.pen_numb);
        penName = (EditText)mView.findViewById(R.id.pen_name);
        final Spinner birdType = (Spinner)mView.findViewById(R.id.birds_type);
        birdNumber = (EditText)mView.findViewById(R.id.birds_number);
        supervisorName = (EditText)mView.findViewById(R.id.supervisor_name);
        Button saveButton = (Button)mView.findViewById(R.id.save_pen);

        if(pen != null){
            penNumber.setText(pen.getPenNum());
            penName.setText(pen.getPenNum());
            birdType.setSelection(2);
            birdNumber.setText(pen.getBirdsNumber());
            supervisorName.setText(pen.getAssignedSupervisor());
        }

        builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(penNumber.getText().toString().isEmpty() ||penName.getText().toString().isEmpty()
                        || birdNumber.getText().toString().isEmpty() ||
                        birdType.getSelectedItem().toString().isEmpty() || supervisorName.getText().toString().isEmpty()){
                    Toast.makeText(mcontext, "Please fill all Details", Toast.LENGTH_SHORT).show();
                }else {
                    Pen newPen = new Pen(Integer.parseInt(birdNumber.getText().toString()), birdType.getSelectedItem().toString(), penName.getText().toString(), supervisorName.getText().toString(), Integer.parseInt(penNumber.getText().toString()));
                    if (pen != null) {
                        newPen.update();
                    }else {
                        newPen.save();
                    }
                    Toast.makeText(mcontext, "Pen saved", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    savedPen =  newPen;


                }
            }
        });


        TextView titleView = new TextView(mcontext);
        if (pen != null) {
            titleView.setText("Edit pen " + pen.getPenNum() + "  "+ pen.getName());
        }else {
            titleView.setText("Add New Pen");
        }
        titleView.setTextSize(14);
        titleView.setTextColor(mcontext.getResources().getColor( R.color.colorAccent));
        titleView.setGravity(Gravity.CENTER);
        alertDialog.setCustomTitle(titleView);
        alertDialog.setView(mView);
        alertDialog.show();
        return      savedPen;
    }
}
