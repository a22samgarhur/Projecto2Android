package com.example.projecto2matesandroid;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static ApiServer apiServer;

    EditText mailText;
    EditText passwordText;
    Usuari usuari = new Usuari();

    //private static final String BASE_URL = "http://10.0.2.2:3450";

    private static final String BASE_URL = "http://math-thai.dam.inspedralbes.cat:3450";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailText = findViewById(R.id.editTextTextEmail);
        passwordText = findViewById(R.id.editTextTextContrasenya);

        SharedPreferences settings = getSharedPreferences("InfoUsuari", 0);
        mailText.setText(settings.getString("Email", "").toString());

        configurarApi();
        Call<Usuari> call = getApiServer().getLogin();
        call.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                Usuari usuariRespuesta = response.body();
                guardarDatos(usuariRespuesta);
                if (usuariRespuesta.getEmail().equals("")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.conexioServer), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void loquearse(View view) {

        usuari.setEmail(mailText.getText().toString());
        usuari.setContrasenaCifrada(passwordText.getText().toString());
        Log.d("user", usuari.getContrasena());

        configurarApi();
        Call<Usuari> call = getApiServer().login(usuari);
        call.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                Usuari usuariRespuesta = response.body();
                Log.e("Usuari", "onResponse: "+usuariRespuesta.getEmail());
                guardarDatos(usuariRespuesta);
                if (usuariRespuesta.getEmail().equals("")) {
                    Toast.makeText(getApplicationContext(),  getString(R.string.ConstrasenyaIncorrecta), Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.conexioServer), Toast.LENGTH_SHORT).show();
            }
        });


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

    //Funcion para guardar los datos que llegan del server en un SharedPreferences
    private void guardarDatos(Usuari respostaUsuari) {
        SharedPreferences settings = getSharedPreferences("InfoUsuari", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("idProfesor", respostaUsuari.getUsuariID());
        editor.putString("nomProfesor", respostaUsuari.getName());
        editor.putString("cognomProfesor", respostaUsuari.getSurname());
        editor.putString("Email", respostaUsuari.getEmail());
        editor.putString("Contrasenya", respostaUsuari.getContrasena());
        editor.commit();

    }

    public static ApiServer getApiServer() {
        return apiServer;
    }
}

