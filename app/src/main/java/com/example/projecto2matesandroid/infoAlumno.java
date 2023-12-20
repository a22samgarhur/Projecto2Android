package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.MainActivity.getApiServer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class infoAlumno extends AppCompatActivity {

    ItemAlumno alumno = new ItemAlumno();
    public static final String EXTRA_MESSAGE ="com.example.android.twoactivities.extra.MESSAGE";
    String idAlumne;
    RecyclerView recyclerViewHistorial;
    MyAdapterHistorial adapter;

    List<ItemHistorial> historials = new ArrayList<ItemHistorial>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alumno);

        Intent intent = getIntent();
        idAlumne = intent.getStringExtra(SecondFragment.EXTRA_MESSAGE);
        //Log.e("ID alumne", "ID Alumne pasado: "+idAlumne );

        recyclerViewHistorial=findViewById(R.id.recyclerviewInfoAlumeHistorial);

        historials.add(new ItemHistorial(" 14-03-2023 14:10 pm","A l'exercici 1, a la Pregunta 3 la resposta a sigut correcta"));
        historials.add(new ItemHistorial(" 20-08-2023","Has guanyat la batalla 3 que ha durat 10 minuts"));
        historials.add(new ItemHistorial(" 19-09-2023","A l'exercici 10, a la Pregunta 5 la resposta a sigut incorrecta"));
        historials.add(new ItemHistorial(" 19-09-2023","A l'exercici 10, a la Pregunta 3 la resposta a sigut correcta"));




        // Crear un adaptador inicial con una lista vacía
        adapter = new MyAdapterHistorial(getApplicationContext(), historials);
        recyclerViewHistorial.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistorial.setAdapter(adapter);

        cogerDatosAlumno();


    }

    //Funcion para hacer una llamada y coger datos del alumno de BD
    public void cogerDatosAlumno() {
        Call <ItemAlumno> call = getApiServer().getAlumne(idAlumne);
        call.enqueue(new Callback<ItemAlumno>() {
            @Override
            public void onResponse(Call<ItemAlumno> call, Response<ItemAlumno> response) {

                if (response.isSuccessful() && response.body() != null) {
                    alumno = response.body();
                    llenarCampos();

                } else {
                    Log.e("Error en respuesta", "Error en response de getAlumne: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ItemAlumno> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.informacioAlumne), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Funcion para llenar los campos del perfil de usuario
    public void llenarCampos(){

        ((TextView) findViewById(R.id.textViewInfoAlumneNom)).setText(alumno.getName()+" "+alumno.getSurname());
        ((TextView) findViewById(R.id.textViewInfoAlumneEmail)).setText(alumno.getEmail());
        ((TextView) findViewById(R.id.textViewInfoAlumneAula)).setText(alumno.getNom_aula());
        ((TextView) findViewById(R.id.textViewInfoAlumneNivell)).setText(getString(R.string.Nivell)+" "+alumno.getLvl());
        ((TextView) findViewById(R.id.textViewInfoAlumneRang)).setText(alumno.getRank());

        String imageUrl = "http://math-thai.dam.inspedralbes.cat:3001/imagen/"+alumno.getImage();

        // Utiliza Glide para cargar la imagen desde la URL
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.cargando) // Placeholder en caso de que la imagen tarde en cargar
                .error(R.drawable.error_imagen) // Imagen de error si falla la carga
                .into(((ImageView) findViewById(R.id.imageViewInfoAlumne)));
    }


    public void tornar(View view) {
        finish();
    }
}