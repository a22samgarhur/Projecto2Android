package com.example.projecto2matesandroid;

import android.os.Bundle;

import com.example.projecto2matesandroid.databinding.ActivityHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homeActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;
    private static ApiServer apiServer;
    private static final String BASE_URL = "http://10.0.2.2:3001";
    Usuari usuari = new Usuari();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAulas);

        List<ItemHome> items = new ArrayList<ItemHome>();
        for (int i = 0; i < 30; i++) {
            String name = "Samuel" + i;
            String email = "Samuel" + i + "@gmail.com";
            items.add(new ItemHome(name, email));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapterHome(getApplicationContext(),items));


        /*RecyclerView recyclerView = findViewById(R.id.recyclerViewAlumnos);
        List<ItemAlumno> alumnes = new ArrayList<ItemAlumno>();
        for (int i = 0; i < 30; i++) {
            String nom = "Mario" + i;
            String nivell = "Nivell " + i + "";
            alumnes.add(new ItemAlumno(nom,nivell,R.drawable.shrek));
        }



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapterAlumno(getApplicationContext(),alumnes));*/
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            /*Intent intent = new Intent(this, Formulari.class);
            intent.putExtra(EXTRA_MESSAGE, nom);
            startActivity(intent);*/

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configurarApi() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(this));
        builder.addInterceptor(new ReceivedCookiesInterceptor(this));
        client = builder.build();


        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Crear una instancia de la interfaz de la API
        apiServer = retrofit.create(ApiServer.class);


    }


    public static ApiServer getApiServer() {
        return apiServer;
    }
}