package com.example.o_d_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.o_d_a.R;

public class MainActivity extends AppCompatActivity {
    public Button button_admin, button_doctor, button_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_doctor = findViewById(R.id.button1);
        //  button_doctor.setOnClickListener(v -> openLogin1());

        button_user = findViewById(R.id.User);
        // button_user.setOnClickListener(v -> openLogin1());

        button_admin = findViewById(R.id.button);
        button_admin.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), admin_Login.class)));

        button_doctor.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, doctor_Login.class)));

        button_user.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, user_Login.class)));

        Uri uri = getIntent().getData();
        if(uri!=null){
            String path =uri.toString();
            Toast.makeText(MainActivity.this,"Path = "+path, Toast.LENGTH_LONG).show();
        }
    }
}