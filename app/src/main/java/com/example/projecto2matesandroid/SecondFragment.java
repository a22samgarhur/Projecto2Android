package com.example.projecto2matesandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecto2matesandroid.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ItemAlumno> alumnesList = new ArrayList<>();
    private MyAdapterAlumno adapter;
    private FragmentSecondBinding binding;
    String aulaID;
    public SecondFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        if (getArguments() != null) {
            aulaID = getArguments().getString("aulaId");
            Log.e("Aula ID", "Aula id: "+aulaID );

        }



        binding = FragmentSecondBinding.inflate(inflater, container, false); // Inicializar el binding

        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerViewAlumnos);


        // Configurar el adaptador del RecyclerView
        adapter = new MyAdapterAlumno(requireContext(), alumnesList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Agregar datos al RecyclerView
        for (int i = 0; i < 30; i++) {
            String nom = "Mario" + i;
            String nivell = "Nivell " + i + "";
            alumnesList.add(new ItemAlumno(nom, nivell, R.drawable.shrek));
        }

        // Notificar al adaptador sobre los cambios en los datos
        adapter.notifyDataSetChanged();

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Obtener la actividad que contiene este fragmento
        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        // Obtener la Toolbar de la actividad
        androidx.appcompat.widget.Toolbar toolbar = activity.findViewById(R.id.toolbar);

        // Verificar si la Toolbar se encontró correctamente antes de configurar el OnClickListener
        if (toolbar != null) {
            // Deshabilitar la funcionalidad de retroceso en la barra de herramientas
            toolbar.setNavigationOnClickListener(null);

            // Manejar el botón de retroceso en el fragmento
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), homeActivity.class);
                    startActivity(intent);
                }
            });
        }


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), homeActivity.class);
                startActivity(intent);
                /*NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);*/
            }
        });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






}