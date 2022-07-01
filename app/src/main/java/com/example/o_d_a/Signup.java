package com.example.o_d_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Signup extends AppCompatActivity {

    private EditText msignupemail, msignuppassword, nameEtv, addressEtv, ageEtv, numberEtv;
    FirebaseAuth mAuth1;
    public Button msignup;
    public Button mgotologin;

    private FirebaseAuth firebaseAuth;
    String userID;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        nameEtv = findViewById(R.id.nameetv);
        msignupemail = findViewById(R.id.emailetv1);
        msignuppassword = findViewById(R.id.passwordetv);
        addressEtv = findViewById(R.id.addressetv1);
        ageEtv = findViewById(R.id.agetv1);
        numberEtv = findViewById(R.id.numberetv1);
        msignup = findViewById(R.id.signup);
        mgotologin = findViewById(R.id.LoginHere);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, user_Login.class);
                startActivity(intent);
            }
        });

        DAOUser dao = new DAOUser();
        msignup.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           String name = nameEtv.getText().toString();
                                           String address = addressEtv.getText().toString();
                                           String age = ageEtv.getText().toString();
                                           String number = numberEtv.getText().toString();
                                           String mail = msignupemail.getText().toString().trim();
                                           String password = msignuppassword.getText().toString().trim();

                                           boolean check = validateInfo(name, mail, password, address, age, number);
                                           User u = new User(name, mail, password, address, age, number);

                                           if (check == true) {
                                               firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                                       if (task.isSuccessful()) {
                                                           Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                                           dao.add(u).addOnSuccessListener(suc -> {
                                                               Toast.makeText(getApplicationContext(), "Data is valid and Record is insert", Toast.LENGTH_SHORT).show();
                                                           }).addOnFailureListener(er ->
                                                           {
                                                               Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                                                           });
                                                           userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                                                           DocumentReference documentReference = fStore.collection("Users").document(userID);
                                                           Map<String, Object> user = new HashMap<>();
                                                           user.put("fName", name);
                                                           user.put("fEmail", mail);
                                                           user.put("fAddress", address);
                                                           user.put("fAge", age);
                                                           user.put("fPassword", password);
                                                           user.put("fNumber", number);
                                                           documentReference.set(user).addOnSuccessListener(a -> {
                                                                   Log.d("TAG", "onSuccess:user Profile is created for " + userID);
                                                               }).addOnFailureListener(e -> Log.d("TAG", "onFailure: " + e.toString()));
                                                               sendEmailVerification();
                                                                   openHome();
                                                               }
                                                           else{
                                                               Toast.makeText(getApplicationContext(), "Failed To Register", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                   });
                                               }
                                        else{
                                                   Toast.makeText(getApplicationContext(), "Sorry Check Information Once", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });
                                   }
                //send email verification
        private void sendEmailVerification ()
        {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Verification Email is Sent,Verify and Log In Again", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Signup.this, user_Login.class));
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Failed To Send Verification Email", Toast.LENGTH_SHORT).show();
            }
        }
        public Boolean validateInfo (String name, String email, String password, String
        address, String age, String number){
            if (name.length() == 0) {
                nameEtv.requestFocus();
                nameEtv.setError("Field Cannot Be Empty");
                return false;
            } else if (!name.matches("[ .a-zA-Z]+")) {
                nameEtv.requestFocus();
                nameEtv.setError("Only Alphabets are Allowed");
                return false;
            } else if (email.length() == 0) {
                msignupemail.requestFocus();
                msignupemail.setError("Field Cannot Be Empty");
                return false;
            } else if (!email.matches("[a-zA-Z0-9,_-]+@[a-z]+\\.+[a-z]+")) {
                msignupemail.requestFocus();
                msignupemail.setError("Enter Valid Email");
                return false;
            } else if (password.length() <= 5) {
                msignuppassword.requestFocus();
                msignuppassword.setError("Minimum 6 characters are required");
                return false;
            } else if (address.length() == 0) {
                addressEtv.requestFocus();
                addressEtv.setError("Field Cannot Be Empty");
                return false;
            } else if (!address.matches("[ .a-zA-Z]+")) {
                addressEtv.requestFocus();
                addressEtv.setError("Only Alphabets are Allowed");
                return false;
            } else if (age.length() == 0) {
                ageEtv.requestFocus();
                ageEtv.setError("Field Cannot Be Empty");
                return false;
            } else if (!age.matches("[0-9]{2}$")) {
                ageEtv.requestFocus();
                ageEtv.setError("atMost 2 digits are allowed");
                return false;
            }
            if (number.length() == 0) {
                numberEtv.requestFocus();
                numberEtv.setError("Field Cannot Be Empty");
                return false;
            } else if (!number.matches("[0-9]{10}$")) {
                numberEtv.requestFocus();
                numberEtv.setError("Fill 10 Digit number");
                return false;
            } else {
                return true;
            }
        }
        public void openHome () {
            Intent intent = new Intent(Signup.this, user_Login.class);
            startActivity(intent);
        }
    }
