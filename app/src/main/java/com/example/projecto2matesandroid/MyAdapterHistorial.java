package com.example.projecto2matesandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterHistorial extends RecyclerView.Adapter<MyViewHolderHistorial> {

    Context context;
    List<ItemHistorial> items;

    public MyAdapterHistorial(Context context, List<ItemHistorial> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderHistorial onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderHistorial(LayoutInflater.from(context).inflate(R.layout.item_historial_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderHistorial holder, int position) {
        holder.horaView.setText(items.get(position).getHora());
        holder.informacionView.setText(items.get(position).getInformacion());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
