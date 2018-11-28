package com.example.hmod_.animeapp.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.hmod_.animeapp.R;

public class dialog_sort extends AppCompatDialogFragment {
    private Button ascending_order;
    private Button descending_order;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view =inflater.inflate(R.layout.activity_dialog_sort, null) ;
        builder.setView(view)
                // Add action buttons
                .setTitle("Sort For Anime")
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog_sort.this.getDialog().cancel();
                    }
                });

        ascending_order = view.findViewById(R.id.ascending_order);
        descending_order = view.findViewById(R.id.descending_order);
        return builder.create();

    }
}
