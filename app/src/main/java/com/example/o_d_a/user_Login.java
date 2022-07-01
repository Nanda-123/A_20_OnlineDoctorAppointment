package com.example.o_d_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_Login extends AppCompatActivity {

    private EditText mloginemail, mloginpassword;
    private Button mlogin, mgotosignup, mgotoforgotpassword;
    private FirebaseAuth firebaseAuth;
    ProgressBar mprogressbarofmainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        getSupportActionBar().hide();

        mloginemail = findViewById(R.id.emailAddressEtv);
        mloginpassword = findViewById(R.id.passwordEtv1);
        mlogin = findViewById(R.id.UserLogin);
        mgotoforgotpassword = findViewById(R.id.button7);
        mgotosignup = findViewById(R.id.button3);

        mprogressbarofmainactivity = findViewById(R.id.pbHeaderProgress);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            finish();
            startActivity(new Intent(user_Login.this, Home.class));
        }

        mgotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_Login.this, Signup.class));
            }
        });

        mgotoforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_Login.this, ForgotPassword.class));
            }
        });


        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mloginemail.getText().toString().trim();
                String password = mloginpassword.getText().toString().trim();

                if (mail.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Field Are Required", Toast.LENGTH_SHORT).show();

                } else {
                    // login the user
                    mprogressbarofmainactivity.setVisibility(View.VISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                checkmailverfication();
                            } else {
                                Toast.makeText(getApplicationContext(), "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                                mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });
    }

    private void checkmailverfication() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser.isEmailVerified() == true) {
            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
            //isUser();
            finish();
            startActivity(new Intent(user_Login.this, Home.class));
        } else {
            mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Verify your mail first", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
//    public void isUser() {
//        String email = mloginemail.getText().toString().trim();
//        String userEnteredPassword = mloginpassword.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
//
//        Query checkUser = reference.orderByChild("mloginemail").equalTo(email);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//
//                    String passwordFromDB = snapshot.child(email).child("mloginpassword").getValue(String.class);
//                    if (passwordFromDB.equals(userEnteredPassword)) {
//                        mloginpassword.setError(null);
//                        //mloginpassword.setErrorEnabled(false);
//
//                        String addressFromDB=snapshot.child(email).child("address").getValue(String.class);
//                        String ageFromDB = snapshot.child(email).child("age").getValue(String.class);
//                        String emailFromDB = snapshot.child(email).child("email").getValue(String.class);
//                        String nameFromDB = snapshot.child(email).child("name").getValue().toString();
//                        String phonenoFromDB = snapshot.child(email).child("number").getValue().toString();
//
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//
//                        intent.putExtra("address", addressFromDB);
//                        intent.putExtra("age", ageFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("name", nameFromDB);
//                        intent.putExtra("number", phonenoFromDB);
//
//                        startActivity(intent);
//
//                        finish();
//
//                    } else {
//                        mloginpassword.setError("Wrong Password");
//                        mloginpassword.requestFocus();
//                    }
//                } else {
//                    //mloginemail.setError("No such user Exist");
//                    //-mloginemail.requestFocus();
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
}







