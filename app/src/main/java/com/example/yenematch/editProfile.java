package com.example.yenematch;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class editProfile extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {

    private EditText editName, editLocation, etNewInterest;
    private ChipGroup chipGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        editName = view.findViewById(R.id.editName);
        editLocation = view.findViewById(R.id.editLocation);
        etNewInterest = view.findViewById(R.id.etNewInterest);
        chipGroup = view.findViewById(R.id.editInterestsChipGroup);

        Button btnAddInterest = view.findViewById(R.id.btnAddInterest);
        Button btnSaveProfile = view.findViewById(R.id.btnSaveProfile);

        // Pre-fill with existing data (Optional: you can pass data via Bundle)
        editName.setText("Kidus Yared");
        editLocation.setText("Addis Ababa, 22");

        btnAddInterest.setOnClickListener(v -> {
            String text = etNewInterest.getText().toString().trim();
            if (!text.isEmpty()) {
                addNewChip(text);
                etNewInterest.setText("");
            }
        });

        btnSaveProfile.setOnClickListener(v -> {
            // Here you would typically send data back to the Profile_page
            dismiss();
        });

        return view;
    }

    private void addNewChip(String text) {
        Chip chip = new Chip(getContext());
        chip.setText(text);
        chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FFD6D6")));
        chip.setTextColor(Color.parseColor("#A52A2A"));

        // This allows the user to "Edit" by removing interests
        chip.setCloseIconVisible(true);
        chip.setCloseIconTint(ColorStateList.valueOf(Color.parseColor("#A52A2A")));
        chip.setOnCloseIconClickListener(v -> chipGroup.removeView(chip));

        chipGroup.addView(chip);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            // Makes the dialog fill the width but wrap the height
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            // Gives the dialog rounded corners from the XML background
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}