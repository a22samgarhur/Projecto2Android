package com.example.projecto2matesandroid;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterHome extends RecyclerView.Adapter<MyViewHolderHome> {

    Context context;
    List<ItemHome> items;
    OnItemClickListener onItemClickListener;

    public MyAdapterHome(Context context, List<ItemHome> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderHome(LayoutInflater.from(context).inflate(R.layout.item_home_view, parent, false));
    }

    // Método para establecer el listener del clic
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderHome holder, int position) {
        holder.idAulaView.setText("ID: " + items.get(position).getId());
        holder.nameAulaView.setText(items.get(position).getName());

        // Asignar clic al elemento del RecyclerView
        holder.homelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    // Llamar al método del listener pasando la posición del elemento clicado

                    onItemClickListener.onItemClick(position);
                    Log.e("Boton", "Boton pulsado: "+items.get(position).getName());
                }
            }
        });
        /*holder.homelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("RecyclerviewAulas", "onClick: Clicada recyclerView de aulas");
                // Asegúrate de que la actividad que contiene el RecyclerView sea una instancia de AppCompatActivity
                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;

                    // Obtener el NavController del NavHostFragment asociado a la actividad
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_home);

                    // Navegar al SecondFragment
                    navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            }

        });*/

    }



    // Interfaz para manejar los clics en el RecyclerView
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItems(List<ItemHome> updatedItems) {
        items.clear(); // Limpiar la lista actual
        items.addAll(updatedItems); // Agregar los nuevos elementos
        notifyDataSetChanged(); // Notificar al adaptador sobre los cambios
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
