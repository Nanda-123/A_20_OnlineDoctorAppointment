package com.example.o_d_a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAddapterr extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;

    public MyAddapterr(Context context, ArrayList<Doctor> doctorArrayList) {
        this.context = context;
        this.doctorArrayList = doctorArrayList;
    }

    ArrayList<Doctor> doctorArrayList;

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Doctor doctor=doctorArrayList.get(position);
        holder.fullName.setText(doctor.fName);
        holder.Email.setText(doctor.fEmail);
        holder.Age.setText(doctor.fAge);
        holder.Address.setText(doctor.fAddress);
        holder.Number.setText(doctor.fNumber);
    }

    @Override
    public int getItemCount() {
        return doctorArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, Email, Age, Address, Number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName=itemView.findViewById(R.id.tvfirstName1);
            Email=itemView.findViewById(R.id.tvEmail1);
            Age=itemView.findViewById(R.id.tvAge1);
            Address=itemView.findViewById(R.id.tvAddress1);
            Number=itemView.findViewById(R.id.tvNumber1);
        }
    }
}
