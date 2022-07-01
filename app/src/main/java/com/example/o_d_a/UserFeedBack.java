package com.example.o_d_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserFeedBack extends AppCompatActivity {

    TextInputEditText editText;
    private EditText email;
    private Button button;
    private FirebaseAuth firebaseAuth;
    String userID;
    FirebaseFirestore fStore;
    ProgressBar mprogressbarofmainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        //getSupportActionBar().hide();

        editText = findViewById(R.id.editText);
        email = findViewById(R.id.email1);
        button = findViewById(R.id.Submit);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mprogressbarofmainactivity = findViewById(R.id.pbHeaderProgress3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String editText1 = editText.getText().toString();

                if (email1.isEmpty() && editText1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Field Are Required", Toast.LENGTH_SHORT).show();

                } else {
                                mprogressbarofmainactivity.setVisibility(View.VISIBLE);
                                userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                                DocumentReference documentReference = fStore.collection("Feedback").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fEmail", email1);
                                user.put("Message", editText1);
                                documentReference.set(user).addOnSuccessListener(a -> {
                                    Log.d("TAG", "onSuccess:user Profile is created for " + userID);
                                    mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Your Response is submitted", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserFeedBack.this, Home.class));
                                }).addOnFailureListener(e -> {
                                    Log.d("TAG", "onFailure: " + e.toString());
                                    Toast.makeText(getApplicationContext(), "There is a error in sending your response, Please resubmit the form", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserFeedBack.this, UserFeedBack.class));
                                    mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                                });
                            }
                        }
                    });
                }
            }
