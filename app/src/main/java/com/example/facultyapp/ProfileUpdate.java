package com.example.facultyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileUpdate extends AppCompatActivity {

//    EditText changePassword,changeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

//        changePassword = findViewById(R.id.change_password);
//        changeEmail = findViewById(R.id.change_email);
//
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String newPassword = "SOME-SECURE-PASSWORD";
//
//        user.updatePassword(newPassword)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        Log.d("TAG", "User password updated.");
//                    }
//                });
//
//        user.updateEmail("user@example.com")
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        Log.d("TAG", "User email address updated.");
//                    }
//                });
   }
}