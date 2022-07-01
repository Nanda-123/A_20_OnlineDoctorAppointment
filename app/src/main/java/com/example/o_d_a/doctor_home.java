package com.example.o_d_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class doctor_home extends AppCompatActivity {

    public Button button,button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        button=findViewById(R.id.logout1);
        button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), doctor_Login.class));
            finish();
        });

        button1=findViewById(R.id.viewProfile);
        button1.setOnClickListener(v -> startActivity(new Intent(doctor_home.this,DoctorProfile.class)));

        button2=findViewById(R.id.viewAppointments1);
        button2.setOnClickListener(v -> startActivity(new Intent(doctor_home.this,ViewAppointments.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home_menu1:
                //Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), doctor_home.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}