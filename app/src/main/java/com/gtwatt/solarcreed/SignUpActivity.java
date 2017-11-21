package com.gtwatt.solarcreed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    PrefManager prefManager;

    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText name,email, phoneNumber;
        prefManager = new PrefManager(this);

        name = (EditText)findViewById(R.id.input_name);
        email = (EditText)findViewById(R.id.input_email);
        phoneNumber = (EditText)findViewById(R.id.input_password);

        signup = (Button)findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etext = email.getText().toString();
                if(!email.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !phoneNumber.getText().toString().isEmpty()  && phoneNumber.getText().toString() != null ) {

                    prefManager.setEmail(email.getText().toString());
                    prefManager.setFarmName(name.getText().toString());
                    prefManager.setPhoneNumber(phoneNumber.getText().toString());
                    Intent intent = new Intent(SignUpActivity.this, SetUpScreenActivity.class);
                    prefManager.setIsSignIn(true);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUpActivity.this, "Please Fill Out Details ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
