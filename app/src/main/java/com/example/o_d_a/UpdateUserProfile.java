package com.example.o_d_a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.Attributes;

public class UpdateUserProfile extends AppCompatActivity {

    private EditText NameEt,AddressEt,AgeEt,PhNumberEt;
    private Button update,btnDelete;
    FirebaseFirestore db;
    FirebaseAuth fAuth1;
    FirebaseUser firebaseuser;
    String userId;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        NameEt=findViewById(R.id.editTextName);
        AddressEt=findViewById(R.id.editTextAddress);
        AgeEt=findViewById(R.id.editTextAge);
        PhNumberEt=findViewById(R.id.editTextPhoneNumber);
        db=FirebaseFirestore.getInstance();
        fAuth1 = FirebaseAuth.getInstance();
        btnDelete= findViewById(R.id.DeleteAccount);
        firebaseuser=fAuth1.getCurrentUser();
        progressbar = findViewById(R.id.pbHeaderProgress5);

        update=findViewById(R.id.UpdateDetails);

        userId = Objects.requireNonNull(fAuth1.getCurrentUser()).getUid();
        DocumentReference ref=db.collection("Users").document(userId);

        btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateUserProfile.this);
            dialog.setTitle("Are you Sure..??");
            dialog.setMessage("Deleting this account will result in completely removing your account"
                    +" from the system and you won't be able to access the app");
            dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    progressbar.setVisibility(View.VISIBLE);
                    firebaseuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressbar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Account is Deleted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UpdateUserProfile.this, user_Login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                });
                    dialog.setNegativeButton("Dismiss",new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    AlertDialog alertDialog=dialog.create();
                    alertDialog.show();


//            ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void unused) {
//                    FirebaseAuth.getInstance().signOut();
//                    Toast.makeText(getApplicationContext(), "Account is Deleted", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(UpdateUserProfile.this,user_Login.class));
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(UpdateUserProfile.this,UpdateUserProfile.class));
//                }
//            });

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = NameEt.getText().toString();
                String address=AddressEt.getText().toString();
                String age=AgeEt.getText().toString();
                String phNumber=PhNumberEt.getText().toString();

                HashMap hashMap = new HashMap();
                hashMap.put("fName",name);
                hashMap.put("fAddress",address);
                hashMap.put("fAge",age);
                hashMap.put("fNumber",phNumber);

                ref.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        progressbar.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Account is Updated", Toast.LENGTH_LONG).show();
                        progressbar.setVisibility(View.GONE);
                        startActivity(new Intent(UpdateUserProfile.this,UserProfile.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(UpdateUserProfile.this,UpdateUserProfile.class));
                    }
                });
            }
        });
    }
}