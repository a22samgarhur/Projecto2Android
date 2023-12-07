package com.example.projecto2matesandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterHome extends RecyclerView.Adapter<MyViewHolderHome> {

    Context context;
    List <ItemHome> items;

    public MyAdapterHome(Context context, List<ItemHome> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderHome(LayoutInflater.from(context).inflate(R.layout.item_home_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderHome holder, int position) {
        holder.nameAulaView.setText(items.get(position).getName());
        holder.idAulaView.setText(items.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
