package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.MainActivity.getApiServer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


    public static final String ACTION_AULACREADA = "com.example.projecto2matesandroid.AULA_CREADA";

    private Context applicationContext;

    Aula aula = new Aula();
    EditText nom;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_aulanueva, null);
        nom = view.findViewById(R.id.novaAulaNom);

        builder.setView(view)
                .setPositiveButton(R.string.Afegir, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        crearAula();
                        //Intent para pasar a la homeActivity un Broadcast
                        Intent intent = new Intent();
                        intent.setAction(ACTION_AULACREADA);
                        requireActivity().sendBroadcast(intent);
                    }
                })
                .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        applicationContext = context.getApplicationContext();
    }

    public void crearAula() {

        aula.setName(nom.getText().toString());

        Log.d("nom aula", aula.getName());


        Call<Aula> call = getApiServer().crearAula(aula);
        call.enqueue(new Callback<Aula>() {
            @Override
            public void onResponse(Call<Aula> call, Response<Aula> response) {
                //Log.e("Crear aula response", "onResponse: "+response );
                Toast.makeText(applicationContext, getString(R.string.aulaCreada), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Aula> call, Throwable t) {
                Toast.makeText(applicationContext, getString(R.string.errorAulaCreada), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
