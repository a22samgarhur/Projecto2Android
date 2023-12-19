package com.example.projecto2matesandroid;

import static com.example.projecto2matesandroid.homeActivity.getApiServer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogRestablirContrasenya extends DialogFragment {

    private Context applicationContext;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_restablircontrasenya, null);

        builder.setView(view)
                .setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

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

    public void novaContrasenya() {
        Call<List<ItemHome>> call = getApiServer().getAulas();
        call.enqueue(new Callback<List<ItemHome>>() {
            @Override
            public void onResponse(Call<List<ItemHome>> call, Response<List<ItemHome>> response) {

                if (response.isSuccessful() && response.body() != null) {


                } else {
                    Log.e("Error en respuesta", "Error en response de getAulas: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ItemHome>> call, Throwable t) {
                Toast.makeText(applicationContext, getString(R.string.ConstrasenyaCoincidencia), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
