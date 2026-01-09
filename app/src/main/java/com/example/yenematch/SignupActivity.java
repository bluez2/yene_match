package com.example.yenematch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

                if(input.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if email exists in the database before proceeding
                    checkEmailInDatabase(input);
                }
            }
        });

        Button btnSignupWithPhone = findViewById(R.id.btnSignupWithPhone);
        btnSignupWithPhone.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, PhoneSignupActivity.class);
            startActivity(intent);
        });
    }

    // Inside checkEmailInDatabase method
    private void checkEmailInDatabase(String email) {
        // Change "RetrofitClient" to "RetroFitClient"
        RetroFitClient.getInterface().checkEmail(email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // ... rest of your code

                if (response.isSuccessful()) {
                    // If PHP returns "new", navigate to Verification
                    // If it returns "exists", tell the user to Login instead
                    Intent intent = new Intent(SignupActivity.this, VerificationActivity.class);
                    intent.putExtra("USER_CONTACT", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignupActivity.this, "Email already registered or Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Connection failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}