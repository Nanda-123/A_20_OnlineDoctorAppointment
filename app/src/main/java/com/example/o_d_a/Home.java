package com.example.o_d_a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Home extends AppCompatActivity {
    public Button button, button1,btn,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,button3,button4;
    private FirebaseAuth firebaseAuth4;
    String userID4;
    FirebaseFirestore fStore4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth4 = FirebaseAuth.getInstance();
        fStore4 = FirebaseFirestore.getInstance();

        button1 = findViewById(R.id.btn1);
        button1.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 10:00am - 10-30am");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                button1.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
//                String btn=button1.getText().toString();
//                userID4 = Objects.requireNonNull(firebaseAuth4.getCurrentUser()).getUid();
//                DocumentReference documentReference = fStore4.collection("Appointments").document(userID4);
//                Map<String, Object> appointment = new HashMap<>();
//                appointment.put("Schedule",btn);
//                documentReference.set(appointment).addOnSuccessListener(a -> {
//                    Log.d("TAG", "onSuccess:user Details recieved for " + userID4);
//                    openUserDetails();
//                }).addOnFailureListener(e -> {
//                    Log.d("TAG", "onFailure: " + e.toString());
//                    Toast.makeText(getApplicationContext(), "There is a error in sending your response, Please resubmit the form", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Home.this, Home.class));
//                })
        });

        button = findViewById(R.id.btn2);
        button.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 10:30am - 11:00am");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                button.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn = findViewById(R.id.btn3);
        btn.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 11:00am - 11:30m");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn1 = findViewById(R.id.btn4);
        btn1.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 11:30am - 12:00pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn1.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn2 = findViewById(R.id.btn5);
        btn2.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 12:00pm - 12:30pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn2.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn3 = findViewById(R.id.btn6);
        btn3.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 12:30pm - 01:00pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn3.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 01:0pm - 01:30pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn4.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn5 = findViewById(R.id.btn8);
        btn5.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 01:30pm - 02:00pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn5.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn6 = findViewById(R.id.btn9);
        btn6.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 05:00pm - 05:30pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn6.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn7 = findViewById(R.id.btn10);
        btn7.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 05:30pm - 06:00pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn7.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn8 = findViewById(R.id.btn11);
        btn8.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 06:00pm - 06:30pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                btn8.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        btn9 = findViewById(R.id.btn12);
        btn9.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 06:30pm - 07-00pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                    btn9.setEnabled(false);
                    openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        button3 = findViewById(R.id.btn13);
        button3.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 07:00pm - 07:30pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                button3.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        });

        button4 = findViewById(R.id.btn14);
        button4.setOnClickListener(v -> {            AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
            dialog.setTitle("Slot at 07:30pm - 08:00pm");
            dialog.setMessage("Are you sure .?? you want to choose this slot"
                    +"then click Yes");
            dialog.setPositiveButton("Yes", (dialog12, which) -> {
                //if (btn9.isPressed()) {
                button4.setEnabled(false);
                openUserDetails();
                //}
            });
            dialog.setNegativeButton("No", (dialog1, which) -> {
                startActivity(new Intent(Home.this,Home.class));
            });
            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
    });

}
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
            case R.id.ChangePwd_menu:
                Toast.makeText(getApplicationContext(), "Item 5 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                return true;
            case R.id.Feedback_menu:menu:
                Toast.makeText(getApplicationContext(), "Item 7 Selected", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), UserFeedBack.class));
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
    public void openUserDetails() {
        Intent intent = new Intent(this, UserDetails.class);
        startActivity(intent);
    }
}

