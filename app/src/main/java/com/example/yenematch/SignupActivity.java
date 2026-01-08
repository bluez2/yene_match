package com.example.yenematch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etSignupEmail;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etSignupEmail = findViewById(R.id.etSignupEmail);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etSignupEmail.getText().toString().trim();

                // Simple validation to check if it's empty
                if(input.isEmpty()){
                    // If email is empty, check if they wanted to use phone (mock logic for now)
                    // In a real app, you would check the phone input field too.
                    Toast.makeText(SignupActivity.this, "Please enter email or phone", Toast.LENGTH_SHORT).show();
                } else {
                    // Navigate to Verification Screen
                    Intent intent = new Intent(SignupActivity.this, VerificationActivity.class);
                    // Pass the email/phone to the next screen so we can show "Sent to user@email.com"
                    intent.putExtra("USER_CONTACT", input);
                    startActivity(intent);
                }
            }
        });
        Button btnSignupWithPhone = findViewById(R.id.btnSignupWithPhone);
        btnSignupWithPhone.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, PhoneSignupActivity.class);
            startActivity(intent);
        });
    }
}