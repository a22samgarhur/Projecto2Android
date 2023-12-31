package com.example.projecto2matesandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

public class DialogQuitarAlumno extends DialogFragment {
    public interface DialogQuitarAlumnoListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    DialogQuitarAlumnoListener listener;

    public void setListener(DialogQuitarAlumnoListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.Quitar_alumne)
                .setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            listener.onDialogPositiveClick(DialogQuitarAlumno.this);
                        } else {
                            Log.e("Error", "Listener es nulo");
                        }
                    }
                })
                .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(DialogQuitarAlumno.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

