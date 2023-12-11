package com.example.projecto2matesandroid;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderHome extends RecyclerView.ViewHolder {
    TextView nameAulaView;
    TextView idAulaView;
    RelativeLayout homelayout;

    public MyViewHolderHome(@NonNull View itemView) {
        super(itemView);
        nameAulaView =itemView.findViewById(R.id.nameAulaView);
        idAulaView =itemView.findViewById(R.id.idAulaView);
        homelayout=itemView.findViewById(R.id.aulaLayout);
    }
}
