package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.MainActivity.getApiServer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecto2matesandroid.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment {

    public static final String EXTRA_MESSAGE ="com.example.android.twoactivities.extra.MESSAGE";

    private RecyclerView recyclerView;
    private List<ItemAlumno> alumnesList = new ArrayList<>();
    private MyAdapterAlumno adapter;
    private FragmentSecondBinding binding;
    private Context applicationContext;
    List<ItemAlumno> alumnes = new ArrayList<ItemAlumno>();
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
        actualizarListaAlumnos();

        // Notificar al adaptador sobre los cambios en los datos
        adapter.notifyDataSetChanged();



        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Obtener la actividad que contiene este fragmento
        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        // Obtener la Toolbar de la actividad
        Toolbar toolbar = activity.findViewById(R.id.toolbar);

        // Verificar si la Toolbar se encontró correctamente antes de configurar el OnClickListener
        if (toolbar != null) {

            // Manejar el botón de retroceso en el fragmento
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireContext(), homeActivity.class);
                    startActivity(intent);
                }
            });
        }

        adapter.setOnItemClickListener(new MyAdapterHome.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String alumneClicat=alumnes.get(position).getId();
                //Log.d("Alumneclicat", "ID Alumneclicat: "+alumneClicat);

                Intent intent = new Intent(requireContext(), infoAlumno.class);
                intent.putExtra(EXTRA_MESSAGE, alumneClicat);
                startActivity(intent);
            }
        });






    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        applicationContext = context.getApplicationContext();
    }

    public void actualizarListaAlumnos() {
        Call<List<ItemAlumno>> call = getApiServer().getAlumnos(aulaID);
        call.enqueue(new Callback<List<ItemAlumno>>() {
            @Override
            public void onResponse(Call<List<ItemAlumno>> call, Response<List<ItemAlumno>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    alumnes = response.body();

                    // Actualizar la lista de items
                    adapter.setItems(alumnes);

                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Log.e("Error en respuesta", "Error en response de getAlumnos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ItemAlumno>> call, Throwable t) {
                Toast.makeText(applicationContext, "No s´ha pogut obtenir els alumnes", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}