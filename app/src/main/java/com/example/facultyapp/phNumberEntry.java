package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class phNumberEntry extends AppCompatActivity {

    EditText PhoneNumber;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_number_entry);

        PhoneNumber = findViewById(R.id.editTextphoneNumber);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        reference = rootRef.child("FacultyDetails");

        findViewById(R.id.button).setOnClickListener(v -> {

            String mobile = PhoneNumber.getText().toString().trim();

            if (mobile.isEmpty() || mobile.length() < 10) {
                PhoneNumber.setError("Enter a valid mobile");
                PhoneNumber.requestFocus();
                return;
            } else {

                if (PhoneNumber.getText().toString().length() > 0) {

                    final String x = PhoneNumber.getText().toString();
                    final String userPhone = x;

                    reference.orderByChild("phone").equalTo(userPhone).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getValue() != null) {
                                for (DataSnapshot ds : snapshot.getChildren()) {

                                    Toast.makeText(phNumberEntry.this, "Registered Number" + ds, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(phNumberEntry.this, FacultyLogin.class);
                                    startActivity(intent);
                                }

                            } else {

                                Intent intent = new Intent(phNumberEntry.this,phone_verify.class);
                                intent.putExtra("mobile", mobile);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
    }
}