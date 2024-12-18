package com.example.facultyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView newUser,logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newUser = findViewById(R.id.textView);
        logIn = findViewById(R.id.textView2);

        newUser.setOnClickListener(v -> {

            Intent i = new Intent(MainActivity.this,phNumberEntry.class);
            startActivity(i);

        });

        logIn.setOnClickListener(v -> {

            Intent i = new Intent(MainActivity.this,FacultyLogin.class);
            startActivity(i);

        });
    }
}