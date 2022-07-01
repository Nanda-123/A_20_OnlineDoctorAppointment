package com.example.o_d_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class admin_Signup extends AppCompatActivity {

    private EditText signupemail, signuppassword, nameEt, addressEt, ageEt, numberEt;
    FirebaseAuth mAuth1;
    public Button signup1;
    public Button gotologin;

    private FirebaseAuth firebaseAuth;
    String userID;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        nameEt = findViewById(R.id.nameet);
        signupemail = findViewById(R.id.emailet);
        signuppassword = findViewById(R.id.passwordet);
        addressEt = findViewById(R.id.addresset);
        ageEt = findViewById(R.id.aget);
        numberEt = findViewById(R.id.numberet);
        signup1 = findViewById(R.id.Signup);
        gotologin = findViewById(R.id.Login1);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_Signup.this, admin_Login.class);
                startActivity(intent);
            }
        });

        DAOUser dao = new DAOUser();
        signup1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        String name = nameEt.getText().toString();
        String address = addressEt.getText().toString();
        String age = ageEt.getText().toString();
        String number = numberEt.getText().toString();
        String mail = signupemail.getText().toString().trim();
        String password = signuppassword.getText().toString().trim();

        boolean check = validateInfo(name, mail, password, address, age, number);
        User u = new User(name, mail, password, address, age, number);

        if (check == true) {
            firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendEmailVerification();
                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        dao.add(u).addOnSuccessListener(suc -> {
                            Toast.makeText(getApplicationContext(), "Data is valid and Record is insert", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er ->
                        {
                            Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                        DocumentReference documentReference = fStore.collection("Admin").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("fName", name);
                        user.put("fEmail", mail);
                        user.put("fAddress", address);
                        user.put("fAge", age);
                        user.put("fPassword", password);
                        user.put("fNumber", number);
                        documentReference.set(user).addOnSuccessListener(a -> {
                            Log.d("TAG", "onSuccess:admin Profile is created for " + userID);
                        }).addOnFailureListener(e -> Log.d("TAG", "onFailure: " + e.toString()));
                        startActivity(new Intent(admin_Signup.this, admin_Login.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed To Register", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Sorry Check Information Once", Toast.LENGTH_SHORT).show();
        }
    }
    });
}

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Verification Email is Sent,Verify and Log In Again", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(admin_Signup.this, admin_Login.class));
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Failed To Send Verification Email", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean validateInfo(String name, String email, String password, String address, String age, String number) {
        if (name.length() == 0) {
            nameEt.requestFocus();
            nameEt.setError("Field Cannot Be Empty");
            return false;
        } else if (!name.matches("[ .a-zA-Z]+")) {
            nameEt.requestFocus();
            nameEt.setError("Only Alphabets are Allowed");
            return false;
        } else if (email.length() == 0) {
            signupemail.requestFocus();
            signupemail.setError("Field Cannot Be Empty");
            return false;
        } else if (!email.matches("[a-zA-Z0-9,_-]+@[a-z]+\\.+[a-z]+")) {
            signupemail.requestFocus();
            signupemail.setError("Enter Valid Email");
            return false;
        } else if (password.length() <= 5) {
            signuppassword.requestFocus();
            signuppassword.setError("Minimum 6 characters are required");
            return false;
        } else if (address.length() == 0) {
            addressEt.requestFocus();
            addressEt.setError("Field Cannot Be Empty");
            return false;
        } else if (!address.matches("[ .a-zA-Z]+")) {
            addressEt.requestFocus();
            addressEt.setError("Only Alphabets are Allowed");
            return false;
        } else if (age.length() == 0) {
            ageEt.requestFocus();
            ageEt.setError("Field Cannot Be Empty");
            return false;
        } else if (!age.matches("[0-9]{2}$")) {
            ageEt.requestFocus();
            ageEt.setError("atMost 2 digits are allowed");
            return false;
        }
        if (number.length() == 0) {
            numberEt.requestFocus();
            numberEt.setError("Field Cannot Be Empty");
            return false;
        } else if (!number.matches("[0-9]{10}$")) {
            numberEt.requestFocus();
            numberEt.setError("Fill 10 Digit number");
            return false;
        } else {
            return true;
        }
    }
}