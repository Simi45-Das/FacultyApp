package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FacultyLogin extends AppCompatActivity {

    EditText IDnumber;
    EditText Password;
    Button login;
    SessionManager sessionManager;
    //    DatabaseReference reference;
    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();

    DatabaseReference reference = rootRef.getReference("FacultyDetails");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        IDnumber =  findViewById(R.id.editTextRollNumber);
        Password = findViewById(R.id.editTextTextPersonName2);
        login = findViewById(R.id.button3);
        sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.CheckedLogin()){
            finish();
        }

        login.setOnClickListener(v -> {

            if (IDnumber.getText().toString().length() > 0 && Password.getText().toString().length() > 0) {

                final String x = IDnumber.getText().toString();
                final String y = Password.getText().toString();
                final String userPass = x + "_" + y;

                reference.orderByChild("id_pass").equalTo(userPass).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            for (DataSnapshot ds : snapshot.getChildren()) {

                                String r = ds.getValue(FregisHelperClass.class).getIDnumber();
                                String p = ds.getValue(FregisHelperClass.class).getPassword();
                                String token = ds.getKey();
                                String d = ds.getValue(FregisHelperClass.class).getSub1();
                                String ed = ds.getValue(FregisHelperClass.class).getSub2();
                                String s3 = ds.getValue(FregisHelperClass.class).getSub3();



                                String ph = ds.getValue(FregisHelperClass.class).getPhone();
                                String n = ds.getValue(FregisHelperClass.class).getName();
                                String em = ds.getValue(FregisHelperClass.class).getEmailId();
                                sessionManager.CreatelogInId(token,r,p,d,ed,s3,ph,n,em);
                                sessionManager.writeLoginSession();
                                Toast.makeText(FacultyLogin.this, "login successful" , Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(FacultyLogin.this, chooseSub.class);
                                startActivity(i);

                            }

                        } else {
                            Toast.makeText(FacultyLogin.this, "Invalid data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        } );
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(FacultyLogin.this, PrivacyStatement.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                Intent i = new Intent(FacultyLogin.this, AboutUs.class);
                startActivity(i);
                return true;

            case R.id.item3:
                Intent intent1 = new Intent(FacultyLogin.this, Rules.class);
                startActivity(intent1);
                return true;

            case R.id.item4:
                Intent intent2 = new Intent(FacultyLogin.this, ProfileUpdate.class);
                startActivity(intent2);
                return true;

            case R.id.item5:

               Intent inte1 = new Intent(FacultyLogin.this,MyProfile.class);
               startActivity(inte1);
               return  true;
            case R.id.item6:

                Intent intentt = new Intent(FacultyLogin.this, EntryRequest.class);
                startActivity(intentt);
                return true;


            case R.id.item7:

                sessionManager = new SessionManager(getApplicationContext());
                sessionManager.LogOut();
                Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}