package com.example.o_d_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class admin_Login extends AppCompatActivity {

    private EditText mloginemail, mloginpassword;
    private Button mlogin, mgotosignup, mgotoforgotpassword;
    private FirebaseAuth firebaseAuth;
    ProgressBar mprogressbarofmainactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

            getSupportActionBar().hide();

            mloginemail = findViewById(R.id.emailAddressEt);
            mloginpassword = findViewById(R.id.passwordEt1);
            mlogin = findViewById(R.id.Login);
            mgotoforgotpassword = findViewById(R.id.ForgotPassword);
            mgotosignup = findViewById(R.id.Register);

            mprogressbarofmainactivity = findViewById(R.id.pbHeaderProgress1);

            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            if (firebaseUser != null) {
                finish();
                startActivity(new Intent(admin_Login.this, admin_home.class));
            }

            mgotosignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(admin_Login.this, admin_Signup.class));
                }
            });

            mgotoforgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(admin_Login.this, ForgotPassword1.class));
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
                startActivity(new Intent(admin_Login.this, admin_home.class));
            } else {
                mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Verify your mail first", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
            }
        }
    }
