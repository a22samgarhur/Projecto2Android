package com.example.projecto2matesandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderHome extends RecyclerView.ViewHolder {
    TextView nameView;
    TextView emailView;

    public MyViewHolderHome(@NonNull View itemView) {
        super(itemView);
        nameView=itemView.findViewById(R.id.nameView);
        emailView=itemView.findViewById(R.id.emailView);
    }
}
