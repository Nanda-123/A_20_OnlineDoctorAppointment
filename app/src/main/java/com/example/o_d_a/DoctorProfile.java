package com.example.o_d_a;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class DoctorProfile extends AppCompatActivity {
    TextInputLayout fullName, email, age, number, address;
    TextView fullNameLabel, emailLabel;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        Toolbar toolbar1 = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar1);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.full_name_profile1);
        email = findViewById(R.id.email_profile1);
        age = findViewById(R.id.age_profile1);
        number = findViewById(R.id.phoneno_profile1);
        address = findViewById(R.id.address_profile1);

        fullNameLabel = findViewById(R.id.Name);
        emailLabel = findViewById(R.id.email8);

        button=findViewById(R.id.Update);
        button.setOnClickListener(v -> startActivity(new Intent(DoctorProfile.this,UpdateDocProfile.class)));

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference1=fStore.collection("Doctor").document(userId);
        documentReference1.addSnapshotListener(this, (value, error) -> {
            fullName.getEditText().setText(value.getString("fName"));
            email.getEditText().setText(value.getString("fEmail"));
            age.getEditText().setText(value.getString("fAge"));
            number.getEditText().setText(value.getString("fNumber"));
            address.getEditText().setText(value.getString("fAddress"));

            fullNameLabel.setText(value.getString("fName"));
            emailLabel.setText(value.getString("fEmail"));
        });

//        DocumentReference documentReference = fStore.collection("Doctor").document(userId);
//        documentReference.addSnapshotListener(this, (value, error) -> {
//            Objects.requireNonNull(number.getEditText()).setText(value.getString("fNumber"));
//            Objects.requireNonNull(fullName.getEditText()).setText(value.getString("fName"));
//            Objects.requireNonNull(email.getEditText()).setText(value.getString("fEmail"));
//            fullNameLabel.setText(value.getString("fName"));
//            emailLabel.setText(value.getString("fEmail"));
//            Objects.requireNonNull(age.getEditText()).setText(value.getString("fAge"));
//            Objects.requireNonNull(address.getEditText()).setText(value.getString("fAddress"));
//        });
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
