package com.example.projecto2matesandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterAlumno extends RecyclerView.Adapter<MyViewHolderAlumno> {
    Context context;
    List<ItemAlumno> alumnos;

    public MyAdapterAlumno(Context context, List<ItemAlumno> alumnos) {
        this.context = context;
        this.alumnos = alumnos;
    }

    @NonNull
    @Override
    public MyViewHolderAlumno onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderAlumno(LayoutInflater.from(context).inflate(R.layout.item_alumno_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAlumno holder, int position) {
        holder.nomView.setText(alumnos.get(position).getNom());
        holder.nivellView.setText(alumnos.get(position).getNivel());
        holder.imatgeView.setImageResource(alumnos.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }
}
