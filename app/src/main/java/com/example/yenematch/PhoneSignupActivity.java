package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_signup);

        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        Button btnContinue = findViewById(R.id.btnContinuePhone);

        btnContinue.setOnClickListener(v -> {
            String phone = etPhoneNumber.getText().toString().trim();

            if (phone.length() < 9) {
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            } else {
                // Navigate to the existing OTP page
                Intent intent = new Intent(PhoneSignupActivity.this, VerificationActivity.class);
                intent.putExtra("USER_CONTACT", "+251 " + phone);
                startActivity(intent);
            }
        });
    }
}