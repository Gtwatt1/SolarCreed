package com.gtwatt.solarcreed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetUpScreenActivity extends AppCompatActivity {

    Button save;
    EditText feedKg, numbBirds;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bird);
        save = (Button)findViewById(R.id.save_utton);
        numbBirds = (EditText)findViewById(R.id.number_birds);
        feedKg = (EditText)findViewById(R.id.number_feeds);
        final String feeds = feedKg.getText().toString();
        final String birds = numbBirds.getText().toString();
        prefManager = new PrefManager(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!numbBirds.getText().toString().isEmpty() && !feedKg.getText().toString().isEmpty()) {

                    prefManager.setBirdTotal(Integer.parseInt(numbBirds.getText().toString()));
                    prefManager.setFeed(Integer.parseInt(feedKg.getText().toString()));
                    prefManager.setDetailedSaved(true);

                    Intent intent = new Intent(SetUpScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SetUpScreenActivity.this, "Please fill out Details ", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
