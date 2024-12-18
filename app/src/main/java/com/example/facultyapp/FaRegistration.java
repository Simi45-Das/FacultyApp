package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FaRegistration extends AppCompatActivity {

    TextView name;
    EditText EmailId;
    EditText Password;
    TextView IDnumber,sub1,sub2,sub3;
    Button register;
    DatabaseReference reference;
    SessionManager sessionManager;
    TextView phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_registration);

         name = findViewById(R.id.tv_name);
         Intent ip = getIntent();
         String na = ip.getStringExtra("name");
         name.setText(na);

        EmailId  = findViewById(R.id.editTextTextEmailAddress);

        Password = findViewById(R.id.editTextTextPassword);

        IDnumber = findViewById(R.id.tv_id);
        Intent innnn = getIntent();
        String id = innnn.getStringExtra("IDnum");
        IDnumber.setText(id);


        register = findViewById(R.id.btn_register);
        
        phone = findViewById(R.id.tv_phone);
        Intent i = getIntent();
        String pn = i.getStringExtra("Phone");
        phone.setText(pn);

        sub1 = findViewById(R.id.tv_cse);
        Intent inn = getIntent();
        String de = inn.getStringExtra("sub1");
        sub1.setText(de);

        sub2 = findViewById(R.id.tv_ete);
        Intent innn = getIntent();
        String se = innn.getStringExtra("sub2");
        sub2.setText(se);

        sub3 = findViewById(R.id.fa_sub3);
        Intent is3 = getIntent();
        String su1 = is3.getStringExtra("sub3");
        sub3.setText(su1);




        sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.CheckedLogin()){
            finish();
        }

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        reference = rootRef.child("FacultyDetails");



    }

    public void InsertRecord (View view){
        if ( !validateEmail() | !validatePassword()  ) {
            return;
        }


        String emailId = EmailId.getText().toString();
        String password = Password.getText().toString();

        String Name = name.getText().toString();
        String IDNumber = IDnumber.getText().toString();


        String Phone = phone.getText().toString();

        String Sub1 = sub1.getText().toString();
        String Sub2 = sub2.getText().toString();
        String Sub3 = sub3.getText().toString();

        final String id_pass = IDNumber + "_" + password;

        FregisHelperClass helperClass = new  FregisHelperClass( Name,IDNumber,Sub1,Sub2,Sub3,emailId, password,Phone,id_pass);
        reference.child(Phone).setValue(helperClass);

        Toast.makeText(FaRegistration.this, "Completed", Toast.LENGTH_SHORT).show();


        EmailId.setText("");
        Password.setText("");
       Intent i = new Intent(FaRegistration.this, chooseSub.class);
       startActivity(i);

}

    private boolean validateEmail () {
        String emailInput = EmailId.getText().toString().trim();
        if (emailInput.isEmpty()) {
            EmailId.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            EmailId.setError("please enter a valid email address");
            return false;
        } else {
            EmailId.setError(null);
            return true;
        }
    }

    private boolean validatePassword () {
        String passwordInput = Password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            Password.setError("Field can't be empty");
            return false;
        } else {
            Password.setError(null);
            return true;
        }

    }
}