package com.example.o_d_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class UpdateDocProfile extends AppCompatActivity {

    private EditText NameEt,AddressEt,AgeEt,PhNumberEt;
    private Button update,btnDelete;
    FirebaseFirestore db1;
    FirebaseAuth fAuth2;
    FirebaseUser firebaseuser1;
    String userId1;
    ProgressBar progressbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doc_profile);

        Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);

        NameEt=findViewById(R.id.editTextName1);
        AddressEt=findViewById(R.id.editTextAddress1);
        AgeEt=findViewById(R.id.editTextAge1);
        PhNumberEt=findViewById(R.id.editTextPhoneNumber1);
        db1=FirebaseFirestore.getInstance();
        fAuth2 = FirebaseAuth.getInstance();
        btnDelete= findViewById(R.id.DeleteAccount1);
        firebaseuser1=fAuth2.getCurrentUser();
        progressbar1 = findViewById(R.id.pbHeaderProgress6);

        update=findViewById(R.id.UpdateDetails1);

        userId1 = Objects.requireNonNull(fAuth2.getCurrentUser()).getUid();
        DocumentReference ref1=db1.collection("Doctor").document(userId1);

        btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateDocProfile.this);
            dialog.setTitle("Are you Sure..??");
            dialog.setMessage("Deleting this account will result in completely removing your account"
                    +" from the system and you won't be able to access the app");
            dialog.setPositiveButton("Delete", (dialog12, which) -> {
                progressbar1.setVisibility(View.VISIBLE);
                firebaseuser1.delete().addOnCompleteListener(task -> {
                    progressbar1.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Account is Deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UpdateDocProfile.this, doctor_Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            });
            dialog.setNegativeButton("Dismiss", (dialog1, which) -> dialog1.dismiss());
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        update.setOnClickListener(v -> {
            String name = NameEt.getText().toString();
            String address=AddressEt.getText().toString();
            String age=AgeEt.getText().toString();
            String phNumber=PhNumberEt.getText().toString();

            HashMap hashMap = new HashMap();
            hashMap.put("fName",name);
            hashMap.put("fAddress",address);
            hashMap.put("fAge",age);
            hashMap.put("fNumber",phNumber);

            ref1.update(hashMap).addOnSuccessListener(o -> {
                progressbar1.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Account is Updated", Toast.LENGTH_LONG).show();
                progressbar1.setVisibility(View.GONE);
                startActivity(new Intent(UpdateDocProfile.this,DoctorProfile.class));
            }).addOnFailureListener(e -> {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(UpdateDocProfile.this,UpdateDocProfile.class));
            });
        });
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