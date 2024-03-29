package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.MainActivity.getApiServer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.projecto2matesandroid.databinding.ActivityHomeBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homeActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    MyAdapterHome adapter;
    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;
    private static ApiServer apiServer;
    //private static final String BASE_URL = "http://10.0.2.2:3450";
    private static final String BASE_URL = "https://math-thai.dam.inspedralbes.cat:3450";
    
    List<ItemHome> items = new ArrayList<ItemHome>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        recyclerView = findViewById(R.id.recyclerViewAulas);
        // Crear un adaptador inicial con una lista vacía
        adapter = new MyAdapterHome(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Establecer el listener para manejar los clics en el RecyclerView
        adapter.setOnItemClickListener(new MyAdapterHome.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String aulaId = items.get(position).getId();

                //Para Bundle pasar elementos al secondfragment
                Bundle bundle = new Bundle();
                bundle.putString("aulaId", aulaId);

                NavController navController = Navigation.findNavController(homeActivity.this, R.id.nav_host_fragment_content_home);
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);
            }
        });

        // Registrar el BroadcastReceiver
        IntentFilter filter = new IntentFilter(DialogNuevaAula.ACTION_AULACREADA);
        registerReceiver(aulaCreatedReceiver, filter);


        configurarApi();
        actualizarListaAulas();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogNuevaAula newFragment = new DialogNuevaAula();
                newFragment.show(getSupportFragmentManager(), "botonEnviarFormulari");
            }
        });
    }

    //Funcion para recibir el Broadcast de DialogNuevaAula,poder ver si hay cambios y actualizar la lista de aulas
    private BroadcastReceiver aulaCreatedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(DialogNuevaAula.ACTION_AULACREADA)) {
                actualizarListaAulas();
            }
        }
    };

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
        if (id == R.id.action_settingsPerfil) {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settingsTancarsesio) {
            logout();
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

    public void actualizarListaAulas() {
        Call<List<ItemHome>> call = getApiServer().getAulas();
        call.enqueue(new Callback<List<ItemHome>>() {
            @Override
            public void onResponse(Call<List<ItemHome>> call, Response<List<ItemHome>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    items = response.body();
                    

                    // Actualizar la lista de items
                    adapter.setItems(items);

                /*for (ItemHome item : items) {
                    Log.d("ItemHome", "Name: " + item.getName() + ", Email: " + item.getId());
                }*/
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Log.e("Error en respuesta", "Error en response de getAulas: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ItemHome>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.obtenirAula, Toast.LENGTH_SHORT).show();
            }
        });

    }  



    public static ApiServer getApiServer() {
        return apiServer;
    }

    public void logout() {

        Call <Void> call = getApiServer().logout();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Intent intent = new Intent(homeActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Log.e("Error en logout", "Error en response de logout: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.errorLogout, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(aulaCreatedReceiver);
    }

    public void onBackPressed() {
        // Aquí puedes manejar el comportamiento cuando se presiona el botón de retroceso
        // Por ejemplo, puedes iniciar la actividad principal cuando se presiona el botón de retroceso desde esta actividad
        super.onBackPressed();
        Intent intent = new Intent(this, homeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Esto limpia el stack de actividades y asegura que MainActivity sea la única en la pila
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }


}