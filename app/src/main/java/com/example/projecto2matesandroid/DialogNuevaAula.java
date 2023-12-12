package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.MainActivity.getApiServer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogNuevaAula extends DialogFragment {

    Aula aula = new Aula();
    EditText nom;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.dialog_aulanueva, null);


        nom = view.findViewById(R.id.novaAulaNom);

        builder.setView(view)
                .setPositiveButton(R.string.Afegir, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        crearAula();
                    }
                })
                .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public void crearAula() {

        aula.setName(nom.getText().toString());

        Log.d("nom aula", aula.getName());


        Call<Aula> call = getApiServer().crearAula(aula);
        call.enqueue(new Callback<Aula>() {
            @Override
            public void onResponse(Call<Aula> call, Response<Aula> response) {

            }

            @Override
            public void onFailure(Call<Aula> call, Throwable t) {
                Toast.makeText(requireActivity().getApplicationContext(), "Error amb la conexio amb el server", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
