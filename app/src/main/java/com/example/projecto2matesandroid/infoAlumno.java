package com.example.projecto2matesandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

public class infoAlumno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alumno);
    }


    public void tornar(View view) {
        NavController navController = Navigation.findNavController(infoAlumno.this, R.id.nav_host_fragment_content_home);
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment);

    }
}