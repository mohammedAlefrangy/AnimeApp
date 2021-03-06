package com.mohammed.hmod_.animeapp.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mohammed.hmod_.animeapp.R;

import java.util.Objects;

public class dialog_sort extends AppCompatDialogFragment {
    private Button ascending_order;
    private Button descending_order;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.activity_dialog_sort, null);
        builder.setView(view)
                // Add action buttons
                .setTitle(R.string.name_of_dialoge)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog_sort.this.getDialog().cancel();
                    }
                });

        ascending_order = view.findViewById(R.id.ascending_order);
        descending_order = view.findViewById(R.id.descending_order);

        if (ascending_order.isClickable()){
            ascendingOrderClicked();
        }else if(descending_order.isClickable()){
            descendingOrderClicked();
        }


        return builder.create();

    }

    private void descendingOrderClicked() {

        descending_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void ascendingOrderClicked() {

        ascending_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


}
