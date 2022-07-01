package com.example.o_d_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class admin_home extends AppCompatActivity {

    public Button button, button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        button=findViewById(R.id.logout);
        button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), admin_Login.class));
            finish();
        });

        button1=findViewById(R.id.viewRegisteredUser);
        button1.setOnClickListener(v -> {
            startActivity(new Intent(admin_home.this, RegisteredUsers.class));
        });

        button2=findViewById(R.id.viewRegisteredDoctor);
        button2.setOnClickListener(v -> {
            startActivity(new Intent(admin_home.this, RegisteredDoctors.class));
        });

        button3=findViewById(R.id.viewFeedBack);
        button3.setOnClickListener(v -> {
            startActivity(new Intent(admin_home.this, ViewFeedback.class));
        });
        button4=findViewById(R.id.viewAppointments);
        button4.setOnClickListener(v -> {
            startActivity(new Intent(admin_home.this, ViewAppointments.class));
        });

        button5=findViewById(R.id.addDoctor);
        button5.setOnClickListener(v -> {
            startActivity(new Intent(admin_home.this, doctor_Signup.class));
        });
    }
}