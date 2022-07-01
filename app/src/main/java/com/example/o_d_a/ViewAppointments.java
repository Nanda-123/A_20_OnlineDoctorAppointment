package com.example.o_d_a;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class ViewAppointments extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Appointments> appointmentsArrayList;
    MYYADDapterr mYYADDapterr;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        appointmentsArrayList= new ArrayList<Appointments>();
        mYYADDapterr=new MYYADDapterr(ViewAppointments.this,appointmentsArrayList);

        recyclerView.setAdapter(mYYADDapterr);
        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("Appointments").orderBy("Name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ProgressDialog progressDialog=new ProgressDialog(ViewAppointments.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Fetching Data....");
                progressDialog.show();
                if(error!=null){
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("FireStore error",error.getMessage());
                    return;
                }
                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        appointmentsArrayList.add(dc.getDocument().toObject(Appointments.class));
                    }
                    mYYADDapterr.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
    }
}