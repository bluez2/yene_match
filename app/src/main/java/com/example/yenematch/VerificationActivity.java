package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private TextView tvUserContact;
    private EditText etOTP;
    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        tvUserContact = findViewById(R.id.tvUserContact);
        etOTP = findViewById(R.id.etOTP);
        btnVerify = findViewById(R.id.btnVerify);

        // Get data from previous Intent
        String contact = getIntent().getStringExtra("USER_CONTACT");
        boolean isFromForgot = getIntent().getBooleanExtra("FROM_FORGOT_PASSWORD", false);

        if (contact != null) {
            tvUserContact.setText(contact);
        }

        btnVerify.setOnClickListener(v -> {
            String code = etOTP.getText().toString();

            // Use "1234" as our test bypass code
            if (code.equals("1234")) {
                if (isFromForgot) {
                    // Path A: User is resetting password
                    Intent intent = new Intent(VerificationActivity.this, ResetPasswordActivity.class);
                    startActivity(intent);
                } else {
                    // Path B: User is signing up for the first time
                    Intent intent = new Intent(VerificationActivity.this, SetupProfileActivity.class);
                    startActivity(intent);
                }
                finish(); // Removes this screen from the "back" stack
            } else {
                Toast.makeText(this, "Incorrect code. Try 1234", Toast.LENGTH_SHORT).show();
            }
        });
        // Inside VerificationActivity.java
        String userEmail = getIntent().getStringExtra("USER_CONTACT");

        btnVerify.setOnClickListener(v -> {
            // After "verifying" the code, move to the final info screen
            Intent intent = new Intent(VerificationActivity.this, SetupProfileActivity.class);
            intent.putExtra("USER_EMAIL", userEmail); // Keep carrying the email!
            startActivity(intent);
        });
    }
}