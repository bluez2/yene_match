package com.example.yenematch;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class SetupProfileActivity2 extends AppCompatActivity {

    private EditText etFullName, etAge, etLocation, etInterestInput;
    private ChipGroup cgInterests;
    private RadioGroup rgGender;
    private Button btnAddInterest, btnFinishSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Linked to your specific XML name
        setContentView(R.layout.activity_setup_profile2);

        // Initialize UI Elements
        etFullName = findViewById(R.id.etFullName);
        etAge = findViewById(R.id.etAge);
        etLocation = findViewById(R.id.etLocation);
        etInterestInput = findViewById(R.id.etInterestInput);
        cgInterests = findViewById(R.id.cgInterests);
        rgGender = findViewById(R.id.rgGender);
        btnAddInterest = findViewById(R.id.btnAddInterest);
        btnFinishSetup = findViewById(R.id.btnFinishSetup);

        // Logic to add chips for interests
        btnAddInterest.setOnClickListener(v -> {
            String interest = etInterestInput.getText().toString().trim();
            if (!interest.isEmpty()) {
                addNewChip(interest);
                etInterestInput.setText("");
            }
        });

        // The button that takes user to Discover Page
        btnFinishSetup.setOnClickListener(v -> {
            if (validateFields()) {
                // Navigate to DiscoverActivity
                Intent intent = new Intent(SetupProfileActivity2.this, DiscoverActivity.class);
                startActivity(intent);

                // Finish this activity so they can't go back to setup
                finish();
            }
        });
    }

    private void addNewChip(String text) {
        Chip chip = new Chip(this);
        chip.setText(text);
        chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FFD6D6")));
        chip.setTextColor(Color.parseColor("#A52A2A"));
        chip.setCloseIconVisible(true);
        chip.setCloseIconTint(ColorStateList.valueOf(Color.parseColor("#A52A2A")));
        chip.setOnCloseIconClickListener(v -> cgInterests.removeView(chip));
        cgInterests.addView(chip);
    }

    private boolean validateFields() {
        if (etFullName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}