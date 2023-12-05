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

    EditText mailText;
    EditText passwordText;
    Usuari usuari = new Usuari();
    private static final String BASE_URL = "http://10.0.2.2:3001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailText = findViewById(R.id.editTextTextEmail);
        passwordText = findViewById(R.id.editTextTextContrasenya);

        SharedPreferences settings = getSharedPreferences("InfoUsuari", 0);

        mailText.setText(settings.getString("Email","").toString());




    }

    public void loquearse(View view) {

        usuari.setEmail(mailText.getText().toString());
        usuari.setContrasena(passwordText.getText().toString());
        Log.d("user", usuari.getContrasena());

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AddCookiesInterceptor(this));
        builder.addInterceptor(new ReceivedCookiesInterceptor(this));
        client = builder.build();


        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz de la API
        ApiServer apiServer = retrofit.create(ApiServer.class);

        Call<Usuari> call = apiServer.login(usuari);
        call.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                Usuari usuariRespuesta = response.body();
                guardarDatos(usuariRespuesta);
                if (usuariRespuesta.getEmail().equals("")) {
                    Toast.makeText(getApplicationContext(), "Usuari o contrasenya incorrecta", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), Aulas.class);
                    startActivity(intent);


                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error amb la conexio amb el server", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Funcion para guardar los datos que llegan del server en un SharedPreferences
    private void guardarDatos(Usuari respostaUsuari) {
            SharedPreferences settings = getSharedPreferences("InfoUsuari", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("idUsuari", respostaUsuari.getUsuariID());
            editor.putString("Email", respostaUsuari.getEmail());
            editor.commit();

    }
}

