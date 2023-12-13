package com.example.projecto2matesandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapterAlumno extends RecyclerView.Adapter<MyViewHolderAlumno> {
    Context context;
    List<ItemAlumno> alumnos;
    MyAdapterHome.OnItemClickListener onItemClickListener;

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
    public void onBindViewHolder(@NonNull MyViewHolderAlumno holder, @SuppressLint("RecyclerView") int position) {
        holder.nomView.setText(alumnos.get(position).getName()+" "+alumnos.get(position).getSurname());
        holder.nivellView.setText(alumnos.get(position).getEmail());

        String imageUrl = "http://10.0.2.2:3001/imagen/"+alumnos.get(position).getImage();

        // Utiliza Glide para cargar la imagen desde la URL
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.cargando) // Placeholder en caso de que la imagen tarde en cargar
                .error(R.drawable.error_imagen) // Imagen de error si falla la carga
                .into(holder.imatgeView);

        // Asignar clic al elemento del RecyclerView
        holder.alumeslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    // Llamar al método del listener pasando la posición del elemento clicado
                    onItemClickListener.onItemClick(position);
                    //Log.e("Boton", "Boton pulsado: "+items.get(position).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public void setItems(List<ItemAlumno> updatedItems) {
        alumnos.clear(); // Limpiar la lista actual
        alumnos.addAll(updatedItems); // Agregar los nuevos elementos
        notifyDataSetChanged(); // Notificar al adaptador sobre los cambios
    }

    public void setOnItemClickListener(MyAdapterHome.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Interfaz para manejar los clics en el RecyclerView
    public interface OnItemClickListener {
        void onItemClick(int position);
    }



}
