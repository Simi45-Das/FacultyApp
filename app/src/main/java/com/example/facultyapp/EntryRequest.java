package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntryRequest extends AppCompatActivity {

    EditText userName,department,IDnumber,phoneNumber;
    Button submit;
    DatabaseReference reference;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_request);

        userName = findViewById(R.id.editTextTextPersonName3);
        department = findViewById(R.id.editTextTextPersonName4);
        IDnumber = findViewById(R.id.editTextTextPersonName5);
        submit = findViewById(R.id.button4);
        phoneNumber = findViewById(R.id.editTextPhone);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        reference = rootRef.child("New Request");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        submit.setOnClickListener(v -> {

        String UserName = userName.getText().toString();
        String IDNumber = IDnumber.getText().toString();
        String Department = department.getText().toString();
        String PhoneNumber = phoneNumber.getText().toString();


        ErequestHelperClass helperClass = new ErequestHelperClass( UserName,Department,IDNumber,PhoneNumber);
        reference.child(String.valueOf(maxid+1)).setValue(helperClass);

        Toast.makeText(EntryRequest.this, "we will notify you", Toast.LENGTH_SHORT).show();

        userName.setText("");
        department.setText("");
        IDnumber.setText("");
        phoneNumber.setText("");
        Intent i = new Intent(EntryRequest.this, MainActivity.class);
        startActivity(i);

    });

    }
}