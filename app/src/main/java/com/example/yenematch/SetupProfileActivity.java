package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SetupProfileActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnFinishSetup;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        // 1. Get the email passed from the previous Verification screen
        userEmail = getIntent().getStringExtra("USER_CONTACT");

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etNewPassword);
        btnFinishSetup = findViewById(R.id.btnFinishSetup);

        btnFinishSetup.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if(username.isEmpty() || pass.isEmpty()){
                Toast.makeText(SetupProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // 2. Pass everything forward to the next setup screen
                Intent intent = new Intent(SetupProfileActivity.this, SetupProfileActivity2.class);
                intent.putExtra("USER_EMAIL", userEmail);
                intent.putExtra("USER_NAME", username);
                intent.putExtra("USER_PASS", pass);
                startActivity(intent);
            }
        });
    }
}