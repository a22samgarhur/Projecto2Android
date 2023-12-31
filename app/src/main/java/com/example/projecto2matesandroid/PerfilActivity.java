package com.example.projecto2matesandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {
    TextView nom,cognoms,email;
    String nomProf,cognomsProf,emailProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nom=findViewById(R.id.textViewPerfilNom);
        cognoms=findViewById(R.id.textViewPerfilCogoms);
        email=findViewById(R.id.textViewPerfilEmail);

        SharedPreferences settings = getSharedPreferences("InfoUsuari", 0);

        nomProf = settings.getString("nomProfesor","Nada");
        cognomsProf = settings.getString("cognomProfesor","Nada");
        emailProf = settings.getString("Email","Nada");

        nom.setText(nomProf);
        cognoms.setText(cognomsProf);
        email.setText(emailProf);

    }

    public void restablecerContrasenya(View view){
        DialogRestablirContrasenya newFragment = new DialogRestablirContrasenya();
        newFragment.show(getSupportFragmentManager(), "restablirConstrasenya");

    }

    public void volver(View view){
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);

    }

}