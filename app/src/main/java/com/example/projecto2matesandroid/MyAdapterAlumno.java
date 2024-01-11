package com.example.projecto2matesandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapterAlumno extends RecyclerView.Adapter<MyViewHolderAlumno> {
    Context context;
    private SecondFragment fragment;
    List<ItemAlumno> alumnos;
    MyAdapterHome.OnItemClickListener onItemClickListener;
    private FragmentManager fragmentManager;

    public MyAdapterAlumno(Context context, List<ItemAlumno> alumnos, FragmentManager fragmentManager,SecondFragment fragment) {
        this.context = context;
        this.alumnos = alumnos;
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
    }

    public MyAdapterAlumno(Context context, List<ItemAlumno> alumnos, FragmentManager fragmentManager) {
        this.context = context;
        this.alumnos = alumnos;
        this.fragmentManager = fragmentManager;
    }

    public MyAdapterAlumno(Context context, List<ItemAlumno> alumnos, SecondFragment fragment) {
        this.context = context;
        this.alumnos = alumnos;
        this.fragment= fragment;
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

        String imageUrl = "https://math-thai.dam.inspedralbes.cat:3450/imagen/"+alumnos.get(position).getImage();

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
                    //Log.e("Boton", "Boton pulsado: "+alumnos.get(position).getName());
                }
            }
        });

        holder.botonquitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager != null) {
                    DialogQuitarAlumno newFragment = new DialogQuitarAlumno();
                    Bundle args = new Bundle();
                    args.putInt("position", position); // Pasar la posición al diálogo
                    newFragment.setArguments(args);
                    newFragment.setListener(new DialogQuitarAlumno.DialogQuitarAlumnoListener() {
                        @Override
                        public void onDialogPositiveClick(DialogFragment dialog) {
                            if (fragment != null) {
                                fragment.quitarAlumno(position);
                            }
                        }
                        @Override
                        public void onDialogNegativeClick(DialogFragment dialog) {
                            // Aquí puedes manejar la lógica si se hace clic en "Cancelar"
                        }
                    });
                    newFragment.show(fragmentManager, "DialogQuitarAlumno");

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
