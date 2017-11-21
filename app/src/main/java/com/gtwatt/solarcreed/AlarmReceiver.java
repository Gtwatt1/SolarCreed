package com.gtwatt.solarcreed;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = intent;
        Bundle extras = intent.getExtras();
        int message = extras.getInt("EverydayAlarm");


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(HomeActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification;
        if(message == 1) {
            notification = builder.setContentTitle("Do Update Your Poultry Records")
                    .setContentText("New Notification From SolarCreed")
                    .setTicker("")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build();
        }else{
            notification = builder.setContentTitle("Get work done guys ")
                    .setContentText("Vaccination time")
                    .setTicker("")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
        Toast.makeText(context,"receied", Toast.LENGTH_SHORT).show();
    }
}