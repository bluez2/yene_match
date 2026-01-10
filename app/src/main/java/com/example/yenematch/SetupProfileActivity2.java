package com.example.yenematch;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupProfileActivity2 extends AppCompatActivity {

    private EditText etFullName, etAge, etLocation, etInterestInput;
    private ChipGroup cgInterests;
    private RadioGroup rgGender;
    private Button btnAddInterest, btnFinishSetup;

    // Variables to hold data from previous screens
    private String email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile2);

        // 1. Retrieve data passed from previous activities
        email = getIntent().getStringExtra("USER_EMAIL");
        username = getIntent().getStringExtra("USER_NAME");
        password = getIntent().getStringExtra("USER_PASS");

        // Initialize UI Elements
        etFullName = findViewById(R.id.etFullName);
        etAge = findViewById(R.id.etAge);
        etLocation = findViewById(R.id.etLocation);
        etInterestInput = findViewById(R.id.etInterestInput);
        cgInterests = findViewById(R.id.cgInterests);
        rgGender = findViewById(R.id.rgGender);
        btnAddInterest = findViewById(R.id.btnAddInterest);
        btnFinishSetup = findViewById(R.id.btnFinishSetup);

        btnAddInterest.setOnClickListener(v -> {
            String interest = etInterestInput.getText().toString().trim();
            if (!interest.isEmpty()) {
                addNewChip(interest);
                etInterestInput.setText("");
            }
        });

        btnFinishSetup.setOnClickListener(v -> {
            if (validateFields()) {
                // 2. Trigger the Database Write
                saveUserToDatabase();
            }
        });
    }

    private void saveUserToDatabase() {
        String fullName = etFullName.getText().toString().trim();
        int age = Integer.parseInt(etAge.getText().toString().trim());
        String location = etLocation.getText().toString().trim();

        // 3. Call Retrofit to write to 'users' and 'profiles' tables
        RetroFitClient.getInterface().createAccount(
                email,
                "0900000000", // Placeholder phone, or add an etPhone field
                password,
                fullName,
                age,
                location
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SetupProfileActivity2.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    // Navigate to DiscoverActivity
                    Intent intent = new Intent(SetupProfileActivity2.this, DiscoverActivity.class);
                    startActivity(intent);
                    finishAffinity(); // Clears all previous activities from stack
                } else {
                    Toast.makeText(SetupProfileActivity2.this, "Registration failed on server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
                Toast.makeText(SetupProfileActivity2.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        if (etAge.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter your age", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}