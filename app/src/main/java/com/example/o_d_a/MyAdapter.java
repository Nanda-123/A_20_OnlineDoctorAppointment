package com.example.o_d_a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    public MyAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    ArrayList<Users> usersArrayList;

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Users users=usersArrayList.get(position);
        holder.fullName.setText(users.fName);
        holder.Email.setText(users.fEmail);
        holder.Age.setText(users.fAge);
        holder.Address.setText(users.fAddress);
        holder.Number.setText(users.fNumber);
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, Email, Age, Address, Number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName=itemView.findViewById(R.id.tvfirstName);
            Email=itemView.findViewById(R.id.tvEmail);
            Age=itemView.findViewById(R.id.tvAge);
            Address=itemView.findViewById(R.id.tvAddress);
            Number=itemView.findViewById(R.id.tvNumber);
        }
    }


}
