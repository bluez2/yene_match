package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SetupProfileActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnFinishSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etNewPassword);
        btnFinishSetup = findViewById(R.id.btnFinishSetup);

        btnFinishSetup.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            if(username.isEmpty() || pass.isEmpty()){
                Toast.makeText(SetupProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = new Intent(SetupProfileActivity.this, SetupProfileActivity2.class);
                startActivity(intent);

                // This is important: it clears the "Signup" screens from history
                finishAffinity();
            }
        });
    }
}