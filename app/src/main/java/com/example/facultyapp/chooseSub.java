package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class chooseSub extends AppCompatActivity {

    TextView subj1,subj2,subj3;
    Button subtn1,subtn2,subtn3;
    SessionManager sessionManager;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sub);

        sessionManager = new SessionManager(getApplicationContext());
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        reference = rootRef.child("VerificationDataFaculty");


        subj1 = findViewById(R.id.tvSub1);
        String s1 = sessionManager.getSubj1();
        subj1.setText(s1);

        subj2 = findViewById(R.id.tvSub2);
        String s2 = sessionManager.getSubj2();
        subj2.setText(s2);

        subj3 = findViewById(R.id.tvSub3);
        String s3 = sessionManager.getSubj3();
        subj3.setText(s3);

        subtn1 = findViewById(R.id.button6);
        subtn2 = findViewById(R.id.button9);
        subtn3 = findViewById(R.id.button10);


  subtn1.setOnClickListener(v -> {
//

      reference.orderByChild("sub1").equalTo(s1).addValueEventListener(new ValueEventListener() {

          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {

              if (snapshot.getValue() != null) {
                  for (DataSnapshot dss : snapshot.getChildren()) {

                      String s1 = dss.getValue(FregisHelperClass.class).getSub1();
//                            String s2 = ds.getValue(StudentHelperClass.class).getSub2();
//                            String s3 = ds.getValue(StudentHelperClass.class).getSub3();

                      Toast.makeText(chooseSub.this, "matched" + dss, Toast.LENGTH_SHORT).show();
                      Intent i = new Intent(chooseSub.this, testcount.class);
                      i.putExtra("sub1", s1);
//                            i.putExtra("sub2",s2);
//                            i.putExtra("sub3",s3);
                      startActivity(i);

                  }
              } else {
                  Toast.makeText(chooseSub.this, "This phone number is not activated", Toast.LENGTH_LONG).show();
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });


  });



    }
}