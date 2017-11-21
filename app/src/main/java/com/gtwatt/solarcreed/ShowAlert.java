package com.gtwatt.solarcreed;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.greenrobot.greendao.annotation.Entity;

/**
 * Created by Gtwatt on 11/4/17.
 */
@Entity
public class ShowAlert {

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
}
