package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.MainActivity.getApiServer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class infoAlumno extends AppCompatActivity {

    ItemAlumno alumno = new ItemAlumno();
    public static final String EXTRA_MESSAGE ="com.example.android.twoactivities.extra.MESSAGE";
    String idAlumne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alumno);

        Intent intent = getIntent();
        idAlumne = intent.getStringExtra(SecondFragment.EXTRA_MESSAGE);
    }


    /*public void cogerDatosAlumno() {
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

    }*/


    public void tornar(View view) {
        finish();
    }
}