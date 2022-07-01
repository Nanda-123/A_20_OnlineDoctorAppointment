package com.example.o_d_a;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;

public class DAOUser {

    private DatabaseReference databasereference;
    public DAOUser()
    {
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databasereference = db.getReference(User.class.getSimpleName());
    }
    public Task<Void> add( User U){
        return databasereference.push().setValue(U);
    }
}
