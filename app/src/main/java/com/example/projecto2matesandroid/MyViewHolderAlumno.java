package com.example.projecto2matesandroid;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderAlumno extends RecyclerView.ViewHolder {

    ImageView imatgeView;
    TextView nomView;
    TextView nivellView;
    ImageButton botonquitar;
    RelativeLayout alumeslayout;

    public MyViewHolderAlumno(@NonNull View itemView) {
        super(itemView);
        imatgeView=itemView.findViewById(R.id.imatgeViewAlumno);
        nomView=itemView.findViewById(R.id.nomAlumnoView);
        nivellView=itemView.findViewById(R.id.nivellAlumnoView);
        botonquitar=itemView.findViewById(R.id.imageButtonQuitarAlumno);
        alumeslayout=itemView.findViewById(R.id.alumnesLayout);
    }
}
