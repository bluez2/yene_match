package com.example.yenematch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;

public class dialog_filters extends BottomSheetDialogFragment {

    private Slider ageSlider;
    private EditText etLocation;
    private ChipGroup interestGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_filters, container, false);

        ageSlider = v.findViewById(R.id.ageSlider);
        etLocation = v.findViewById(R.id.etFilterLocation);
        interestGroup = v.findViewById(R.id.filterInterestGroup);
        Button btnApply = v.findViewById(R.id.btnApplyFilters);

        btnApply.setOnClickListener(view -> {
            // Get values
            float selectedAge = ageSlider.getValue();
            String location = etLocation.getText().toString().trim();

            // Get selected chip text
            int checkedChipId = interestGroup.getCheckedChipId();
            String selectedInterest = "";
            if (checkedChipId != View.NO_ID) {
                Chip chip = v.findViewById(checkedChipId);
                selectedInterest = chip.getText().toString();
            }

            // For now, we show a toast with the selected filters
            String message = "Age: " + (int)selectedAge + "\nLoc: " + location + "\nInt: " + selectedInterest;
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

            dismiss();
        });

        return v;
    }
}