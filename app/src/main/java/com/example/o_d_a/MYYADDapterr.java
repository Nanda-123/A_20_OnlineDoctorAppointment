package com.example.o_d_a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MYYADDapterr extends RecyclerView.Adapter<MYYADDapterr.MyViewHolder>{
    Context context;

    public MYYADDapterr(Context context, ArrayList<Appointments> appointmentsArrayList) {
        this.context = context;
        this.appointmentsArrayList = appointmentsArrayList;
    }
    ArrayList<Appointments> appointmentsArrayList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item4,parent,false);

        return new MYYADDapterr.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Appointments appointments=appointmentsArrayList.get(position);
        holder.Name1.setText(appointments.Name);
        holder.Email1.setText(appointments.Email);
        holder.Age1.setText(appointments.Age);
        holder.BloodGrp1.setText(appointments.BloodGrp);
        holder.Gender1.setText(appointments.Gender);
        holder.Number1.setText(appointments.Number);
        holder.City1.setText(appointments.City);
        holder.Address1.setText(appointments.Address);
    }

    @Override
    public int getItemCount() {
        return appointmentsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name1,Email1, Age1, BloodGrp1, Gender1, Number1,City1,Address1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name1=itemView.findViewById(R.id.itemName);
            Email1=itemView.findViewById(R.id.itemEmail);
            Age1=itemView.findViewById(R.id.itemAge);
            BloodGrp1=itemView.findViewById(R.id.itemBloodGroup);
            Gender1=itemView.findViewById(R.id.itemGender);
            Number1=itemView.findViewById(R.id.itemNumber);
            City1=itemView.findViewById(R.id.itemCity);
            Address1=itemView.findViewById(R.id.itemAddress);
        }
    }
}
