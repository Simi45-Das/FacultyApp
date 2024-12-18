package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class testcount extends AppCompatActivity {


    TextView q1tv1, q1tv2, q1tv3, tv_subj,q2tv1, fac,q2tv2, t1, t2, t3,r1Op2,r1Op3, q5tv5, q6tv6, b1, b2, b3;
    //FormHelperClass f;
    DatabaseReference fRef, form1, form2, form3, form4, form5, form6, form7, form8;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    SessionManager ss1;

    int currentent=0;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcount);

        form1 = db.getReference("FORM");

        b3 = findViewById(R.id.userName);

        ss1 = new SessionManager(getApplicationContext());

        final String idNum = ss1.getIDNum();

        b3.setText(idNum);


        t1 = findViewById(R.id.q1test1);
//        t2 = findViewById(R.id.q1test2);
//        t3 = findViewById(R.id.q1test3);
        fac = findViewById(R.id.f1test1);

        t2 = findViewById(R.id.f1test3);
        t3 = findViewById(R.id.f1test4);


        q1tv1 = findViewById(R.id.q1_r1);
        q1tv2 = findViewById(R.id.q1_r3);
        q1tv3 = findViewById(R.id.q1_r2);

        tv_subj = findViewById(R.id.tvSubj);
        Intent i1 = getIntent();
        String su1 = i1.getStringExtra("sub1");
        tv_subj.setText(su1);


        if (su1 != null) {
            ArrayList<FeedbackObject> feedbacklist = new ArrayList<>();
            form1.orderByChild("subject").equalTo(su1).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot dsmain: snapshot.getChildren()) {
                        String ida = dsmain.child("subject").getValue(String.class);
                        String idb = su1;
                        if (ida.equals(idb)) {
                            FeedbackObject feedbackObject = new FeedbackObject();
                            feedbackObject.rg01 = dsmain.child("rg_question01").getValue(String.class);
                            feedbackObject.rg02 = dsmain.child("rg_question02").getValue(String.class);
                            feedbackObject.rg03 = dsmain.child("rg_question03").getValue(String.class);
                            feedbackObject.rg04 = dsmain.child("rg_question04").getValue(String.class);
                            feedbackObject.rg05 = dsmain.child("rg_question05").getValue(String.class);
                            feedbackObject.rg06 = dsmain.child("rg_question06").getValue(String.class);
                            feedbackObject.rg07 = dsmain.child("rg_question07").getValue(String.class);
                            feedbackObject.rg08 = dsmain.child("rg_question08").getValue(String.class);
                            feedbackObject.rg09 = dsmain.child("rg_question09").getValue(String.class);
                            feedbackObject.rg10 = dsmain.child("rg_question10").getValue(String.class);
                            feedbackObject.ml_tv = dsmain.child("ml_tv").getValue(String.class);
                            feedbacklist.add(feedbackObject);
                        }
                    }
                    helperFeedback(feedbacklist);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(testcount.this, PrivacyStatement.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                Intent i = new Intent(testcount.this, AboutUs.class);
                startActivity(i);
                return true;

            case R.id.item3:
                Intent intent1 = new Intent(testcount.this, Rules.class);
                startActivity(intent1);
                return true;

            case R.id.item4:
                Intent intent2 = new Intent(testcount.this, ProfileUpdate.class);
                startActivity(intent2);
                return true;

            case R.id.item5:


                   Intent inte1 = new Intent(testcount.this,MyProfile.class);
                   startActivity(inte1);
                   return  true;

            case R.id.item6:

                Intent intentt = new Intent(testcount.this, EntryRequest.class);
                startActivity(intentt);
                return true;


            case R.id.item7:

                ss1 = new SessionManager(getApplicationContext());
                ss1.LogOut();
                Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
}

    public void helperFeedback(ArrayList<FeedbackObject> mylist){
        int totalFeedbackCount = mylist.size();
        int sometimes_rg01 = 0;
        int never_rg01 = 0;
        int always_rg01 = 0;
        int excellent_rg02 = 0;
        int veryGood_rg02 = 0;
        for (FeedbackObject obj: mylist){
            if (obj.rg01.equals("Sometime")){
                sometimes_rg01 ++;
                t1.setText(Integer.toString( sometimes_rg01 ));
            }
            else
            {
                t1.setText(Integer.toString(0));
            }
            if (obj.rg01.equals("Never")){
                never_rg01 ++;
                t3.setText(Integer.toString(never_rg01));
            }
            else {
                t3.setText(Integer.toString(0));
            }
            if (obj.rg01.equals("Always")){
                always_rg01 ++;
                t2.setText(Integer.toString(always_rg01));
            }
            else{
                t2.setText(Integer.toString(0));
            }
            if (obj.rg02.equals("Excellent")){
                excellent_rg02 ++;
            }
            if (obj.rg02.equals("Very Good")){
                veryGood_rg02 ++;
            }
        }
  fac.setText(Integer.toString(totalFeedbackCount));

        Double p = Double.parseDouble(t1.getText().toString());
      Double z = Double.parseDouble(t2.getText().toString());
        Double a = Double.parseDouble(t3.getText().toString());
        Double f = Double.parseDouble(fac.getText().toString());
        Double avg = ((p / f) * 100);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String per = decimalFormat.format(avg);
        q1tv1.setText(per + "%");


        Double avg1 = ((z / f) * 100);
        DecimalFormat decimalFormat1 = new DecimalFormat("#.00");
        String per1 = decimalFormat1.format(avg1);
        q1tv3.setText(per1 + "%");

        Double avg2 = ((a / f) * 100);
        DecimalFormat decimalFormat2 = new DecimalFormat("#.00");
        String per2 = decimalFormat2.format(avg2);
        q1tv2.setText(per2 + "%");


    }


}
