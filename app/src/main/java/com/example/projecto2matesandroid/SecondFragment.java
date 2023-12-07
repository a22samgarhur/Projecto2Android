package com.example.projecto2matesandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
    public SecondFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

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