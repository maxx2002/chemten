package com.example.chemten.view.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.chemten.R;

public class BackDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.back_dialog, null);
        TextView btn_no = view.findViewById(R.id.btn_no_back_dialog);
        TextView btn_yes = view.findViewById(R.id.btn_yes_back_dialog);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackDialog.this.getDialog().dismiss();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                BackDialog.this.getDialog().dismiss();
            }
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
