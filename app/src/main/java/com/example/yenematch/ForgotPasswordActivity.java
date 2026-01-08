package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etForgotInput;
    private Button btnSendResetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etForgotInput = findViewById(R.id.etForgotInput);
        btnSendResetCode = findViewById(R.id.btnSendResetCode);

        btnSendResetCode.setOnClickListener(v -> {
            String contact = etForgotInput.getText().toString().trim();

            if (contact.isEmpty()) {
                Toast.makeText(this, "Please enter your email or phone", Toast.LENGTH_SHORT).show();
            } else {
                // Pass data to VerificationActivity
                Intent intent = new Intent(ForgotPasswordActivity.this, VerificationActivity.class);
                intent.putExtra("USER_CONTACT", contact);
                // This boolean tells the next screen to go to ResetPasswordActivity afterward
                intent.putExtra("FROM_FORGOT_PASSWORD", true);
                startActivity(intent);
            }
        });
    }
}