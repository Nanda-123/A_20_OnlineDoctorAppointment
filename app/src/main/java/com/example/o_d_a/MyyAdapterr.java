package com.example.o_d_a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyyAdapterr extends RecyclerView.Adapter<MyyAdapterr.MyViewHolder>{

    Context context2;

    public MyyAdapterr(Context context1, ArrayList<Feedback> feedbackArrayList) {
        this.context2 = context1;
        this.feedbackArrayList = feedbackArrayList;
    }
    ArrayList<Feedback> feedbackArrayList;

    @NonNull
    @Override
    public MyyAdapterr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context2).inflate(R.layout.item3,parent,false);

        return new MyyAdapterr.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyyAdapterr.MyViewHolder holder, int position) {
        Feedback feedback=feedbackArrayList.get(position);
        holder.Email.setText(feedback.fEmail);
        holder.Message1.setText(feedback.Message);
    }

    @Override
    public int getItemCount() {
        return feedbackArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Email, Message1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Email=itemView.findViewById(R.id.tvEmail2);
            Message1 =itemView.findViewById(R.id.tvMessage);

        }
    }
}
