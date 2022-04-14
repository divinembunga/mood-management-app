package com.example.moodmanagementapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import yuku.ambilwarna.AmbilWarnaDialog;

// Settings colour customisation page
public class ColourChange extends Fragment {

    View view;
    View colourView;
    ImageButton editButton;


    public ColourChange() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_colour_change, container, false);
        colourView = view.findViewById(R.id.set_colour_one);
        editButton = view.findViewById(R.id.btn_edit_colour);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColourPicker();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void openColourPicker(){
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(this.getContext(), R.color.colorVeryHappy,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        colourView.setBackgroundColor(color);

                    }
                });
        colorPickerDialogue.show();
    }
}
