package com.example.projecto2matesandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolderHistorial extends RecyclerView.ViewHolder {
    TextView horaView,informacionView,correctaView;

    public MyViewHolderHistorial(@NonNull View itemView) {
        super(itemView);
        horaView=itemView.findViewById(R.id.textViewHoraHistorial);
        informacionView=itemView.findViewById(R.id.textViewInfoHistorial);
    }
}
