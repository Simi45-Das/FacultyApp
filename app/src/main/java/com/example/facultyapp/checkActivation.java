package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class checkActivation extends AppCompatActivity {

    TextView PhoneNumber;
    TextView Check;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_activation);

        PhoneNumber = findViewById(R.id.textView8);
        Check = findViewById(R.id.button5);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        reference = rootRef.child("VerificationDataFaculty");

        Intent i = getIntent();
        String pn = i.getStringExtra("mobile");
        PhoneNumber.setText(pn);

        Check.setOnClickListener(v -> {

//            Toast.makeText(CheckActivation.this,"hii",Toast.LENGTH_LONG).show();

            reference.orderByChild("phone").equalTo(pn).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue() != null) {
                        for (DataSnapshot ds : snapshot.getChildren()) {

                            String r = ds.getValue(FregisHelperClass.class).getIDnumber();
                            String c = ds.getValue(FregisHelperClass.class).getSub1();
                            String e = ds.getValue(FregisHelperClass.class).getSub2();
                            String s3 = ds.getValue(FregisHelperClass.class).getSub3();
                            String p = ds.getValue(FregisHelperClass.class).getPhone();

                            String n = ds.getValue(FregisHelperClass.class).getName();



                            Toast.makeText(checkActivation.this, "matched" + ds, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(checkActivation.this, FaRegistration.class);
                            i.putExtra("IDnum", r);
                            i.putExtra("sub1",c);
                            i.putExtra("sub2",e);
                            i.putExtra("sub3",s3);
                            i.putExtra("name",n);
                            i.putExtra("Phone",p);
                            startActivity(i);

                        }
                    } else {
                        Toast.makeText(checkActivation.this,"please enter valid phon",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });


    }
}