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
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, age, number, address;
    TextView fullNameLabel, emailLabel;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    private String email1;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS ="Users";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        age = findViewById(R.id.age_profile);
        number = findViewById(R.id.phoneno_profile);
        address = findViewById(R.id.address_profile);
        button=findViewById(R.id.button2);
        button.setOnClickListener(v -> startActivity(new Intent(UserProfile.this,UpdateUserProfile.class)));
        fullNameLabel = findViewById(R.id.full_name);
        emailLabel = findViewById(R.id.email2);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, (value, error) -> {
                Objects.requireNonNull(number.getEditText()).setText(value.getString("fNumber"));
                Objects.requireNonNull(fullName.getEditText()).setText(value.getString("fName"));
                Objects.requireNonNull(email.getEditText()).setText(value.getString("fEmail"));
                fullNameLabel.setText(value.getString("fName"));
                emailLabel.setText(value.getString("fEmail"));
                Objects.requireNonNull(age.getEditText()).setText(value.getString("fAge"));
                Objects.requireNonNull(address.getEditText()).setText(value.getString("fAddress"));
        });
    }
//        showAllUserData(); }
//    public void showAllUserData() {
//        Intent intent = getIntent();
//        String address1 = intent.getStringExtra("address");
//        String age1 = intent.getStringExtra("age");
//        String email1 = intent.getStringExtra("email");
//        String name = ((Intent) intent).getStringExtra("name");
//        String phoneno = intent.getStringExtra("number");
//
//        fullNameLabel.setText(name);
//        emailLabel.setText(email1);
//        address.getEditText().setText(address1);
//        age.getEditText().setText(age1);
//        email.getEditText().setText(email1);
//        fullName.getEditText().setText(name);
////        number.getEditText().setText(phoneno);
//        Intent intent = getIntent();
//        email1 = intent.getStringExtra("fEmail");
//
//        database = FirebaseDatabase.getInstance();
//        DatabaseReference userRef = database.getReference(USERS);
//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    if (ds.child("email").getValue().equals(email1)) {
//                        fullNameLabel.setText(ds.child("name").getValue(String.class));
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        }) ;
//    }
    //}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home_menu:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Home.class));
                return true;
            case R.id.person_menu:
                Toast.makeText(getApplicationContext(), "Item 7 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                return true;
            case R.id.contact_menu:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ContactUs.class));
                return true;
//            case R.id.help_menu:
//                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
//                //startActivity(new Intent(getApplicationContext(), Home.class));
//                return true;
//            case R.id.Health_menu:
//                Toast.makeText(getApplicationContext(), "Item 4 Selected", Toast.LENGTH_LONG).show();
//                //startActivity(new Intent(getApplicationContext(), Home.class));
//                return true;
            case R.id.Feedback_menu:
                menu:
                Toast.makeText(getApplicationContext(), "Item 7 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), UserFeedBack.class));
                return true;
            case R.id.ChangePwd_menu:
                Toast.makeText(getApplicationContext(), "Item 5 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                return true;
            case R.id.logout_menu:
                Toast.makeText(getApplicationContext(), "Item 6 Selected", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), user_Login.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}