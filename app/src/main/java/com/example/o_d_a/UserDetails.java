package com.example.o_d_a;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserDetails extends AppCompatActivity {

    public Button button,button1;
    EditText nameEtv, emailEtv, cityEtv, addressEtv, ageEtv, numberEtv;
    private FirebaseAuth firebaseAuth;
    String userID;
    FirebaseFirestore fStore;
    private Spinner spinner;
    private RadioButton female, male;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        nameEtv=findViewById(R.id.nameetv1);
        emailEtv=findViewById(R.id.emailetv);
        cityEtv=findViewById(R.id.cityetv);
        addressEtv=findViewById(R.id.addressetv);
        ageEtv=findViewById(R.id.agetv);
        numberEtv=findViewById(R.id.numberetv);
        spinner=findViewById(R.id.spinner);
        button = findViewById(R.id.b2);
        male=findViewById(R.id.radioButton);
        female=findViewById(R.id.radioButton2);
        button1=findViewById(R.id.btn1);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        button.setOnClickListener(v -> {
            String name=nameEtv.getText().toString();
            String email=emailEtv.getText().toString();
            String city=cityEtv.getText().toString();
            String address=addressEtv.getText().toString();
            String age=ageEtv.getText().toString();
            String number=numberEtv.getText().toString();
            String spinner1=spinner.getSelectedItem().toString();
            String male1=male.getText().toString();
            String female1=female.getText().toString();

            boolean check= validateData(name,email,city,address,age,number);

            if(check==true) {
                Toast.makeText(getApplicationContext(), "Data is Valid", Toast.LENGTH_SHORT).show();
                if(email.isEmpty()){
                    startActivity(new Intent(UserDetails.this, UserDetails.class));
                }
                else {
                userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                DocumentReference documentReference = fStore.collection("Appointments").document(userID);
                Map<String, Object> appointment = new HashMap<>();
                appointment.put("Name", name);
                appointment.put("Email",email);
                appointment.put("City",city);
                appointment.put("Address",address);
                appointment.put("Age",age);
                appointment.put("Number",number);
                appointment.put("BloodGrp",spinner1);
                if(male.isChecked()){
                    appointment.put("Gender",male1);
                }else{
                    appointment.put("Gender",female1);
                }
                documentReference.set(appointment).addOnSuccessListener(a -> {
                    Log.d("TAG", "onSuccess:user Details recieved for " + userID);
                    //mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Your Response is submitted", Toast.LENGTH_SHORT).show();
//                    button1 = findViewById(R.id.btn1);
//                    button1.setOnClickListener(V -> {
//                                if (button1.isPressed()) {
//                                    button1.setEnabled(false);
//                                    //openUserDetails();
//                                    startActivity(new Intent(UserDetails.this, Home.class));
//                                }
//                            });
                    openPay();
                    //startActivity(new Intent(UserDetails.this, Home.class));
                }).addOnFailureListener(e -> {
                    Log.d("TAG", "onFailure: " + e.toString());
                    Toast.makeText(getApplicationContext(), "There is a error in sending your response, Please resubmit the form", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserDetails.this, UserDetails.class));
                    //mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                });
            } }
            else{
                    Toast.makeText(getApplicationContext(),"Sorry Check Information Once",Toast.LENGTH_SHORT).show();
                }
            });
        }
    public Boolean validateData(String name, String email, String city, String address, String age, String number) {
        if (name.length() == 0) {
            nameEtv.requestFocus();
            nameEtv.setError("Field Cannot Be Empty");
            return false;
        } else if (!name.matches("[ .a-zA-Z]+")) {
            nameEtv.requestFocus();
            nameEtv.setError("Only Alphabets are Allowed");
            return false;
        } else if (email.length() == 0) {
            emailEtv.requestFocus();
            emailEtv.setError("Field Cannot Be Empty");
            return false;
        } else if (!email.matches("[a-zA-Z0-9,_-]+@[a-z]+\\.+[a-z]+")) {
            emailEtv.requestFocus();
            emailEtv.setError("Enter Valid Email");
            return false;
        } else if(city.length() == 0) {
            cityEtv.requestFocus();
            cityEtv.setError("Field Cannot Be Empty");
            return false;
        } else if (!name.matches("[ .a-zA-Z]+")) {
            cityEtv.requestFocus();
            cityEtv.setError("Only Alphabets are Allowed");
            return false;
        } else if(address.length() == 0) {
            addressEtv.requestFocus();
            addressEtv.setError("Field Cannot Be Empty");
            return false;
        } else if (!address.matches("[ .a-zA-Z]+" )) {
            addressEtv.requestFocus();
            addressEtv.setError("Only Alphabets are Allowed");
            return false;
        }else if (age.length() == 0) {
            ageEtv.requestFocus();
            ageEtv.setError("Field Cannot Be Empty");
            return false;
        } else if(!age.matches("[0-9]{2}$")){
            ageEtv.requestFocus();
            ageEtv.setError("atMost 2 digits are allowed");
            return false;
        }  if (number.length() == 0) {
            numberEtv.requestFocus();
            numberEtv.setError("Field Cannot Be Empty");
            return false;
        } else if(!number.matches("[0-9]{10}$")){
            numberEtv.requestFocus();
            numberEtv.setError("Fill 10 Digit number");
            return false;
        }
        else {
            return true;
        }
    }
    public void openPay() {
        Intent intent = new Intent(this, RazorPayPayment.class);
        startActivity(intent);
    }
}


