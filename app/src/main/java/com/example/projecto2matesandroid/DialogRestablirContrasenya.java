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
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogRestablirContrasenya extends DialogFragment {

    private Context applicationContext;
    EditText contraAntiga, contraNova;
    actualizarConstrasenyaModel actualizar = new actualizarConstrasenyaModel();
    private AlertDialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_restablircontrasenya, null);
        contraAntiga = view.findViewById(R.id.restablirContrasenyaAntiga);
        contraNova = view.findViewById(R.id.restablirContrasenyaNova);


        builder.setView(view)
                .setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                AlertDialog alertDialog = (AlertDialog) dialogInterface;
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        novaContrasenya();

                    }
                });
            }
        });

        return dialog;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        applicationContext = context.getApplicationContext();
    }

    public void novaContrasenya() {
        //Log.e("Antigacontrasenya", "AntigaContrasenya: "+contraAntiga.getText().toString());
        //Log.e("Novacontrasenya", "novaContrasenya: "+contraNova.getText().toString());

        String contrasenyaNova = contraNova.getText().toString();

        if (contrasenyaNova.isEmpty()) {
            Toast.makeText(applicationContext, getString(R.string.ConstrasenyaBuida), Toast.LENGTH_SHORT).show();
        } else {
            actualizar.setContrasenyaAntigua(contraAntiga.getText().toString());
            actualizar.setContrasenyaNueva(contrasenyaNova);


            Call<actualizarConstrasenyaModel> call = getApiServer().restablecerConstrasenya(actualizar);
            call.enqueue(new Callback<actualizarConstrasenyaModel>() {
                @Override
                public void onResponse(Call<actualizarConstrasenyaModel> call, Response<actualizarConstrasenyaModel> response) {

                    if (response.isSuccessful() && response.body() != null && !contraNova.equals("")) {
                        Toast.makeText(applicationContext, R.string.ConstrasenyaCambiada, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(applicationContext, R.string.ConstrasenyaCoincidencia, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<actualizarConstrasenyaModel> call, Throwable t) {
                    Toast.makeText(applicationContext, R.string.ConstrasenyaCoincidencia, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
