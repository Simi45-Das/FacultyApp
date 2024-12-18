package com.example.facultyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    TextView userName,IDnumber,email,phoneNumber;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        userName = findViewById(R.id.tv_name1);
        IDnumber = findViewById(R.id.tv_idnum);
        email = findViewById(R.id.tv_email);
        phoneNumber = findViewById(R.id.tv_pn);

        sessionManager = new SessionManager(getApplicationContext());

        String pn =   sessionManager.getName();
        userName.setText(pn);

        String ID =   sessionManager.getIDNum();
        IDnumber.setText(ID);

        String email1 =   sessionManager.getemailId();
        email.setText(email1);

        String mobile =   sessionManager.getPhn();
        phoneNumber.setText(mobile);


    }
}