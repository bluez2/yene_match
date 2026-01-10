package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        // Fix: Use the correct ID for system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_settings_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Setup Card Clicks
        setupCardClicks();

        // 2. Logout Logic
        CardView cardLogout = findViewById(R.id.cardLogout);
        cardLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
            // Go back to Login Screen
            Intent intent = new Intent(Settings.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setupCardClicks() {
        findViewById(R.id.cardPrivacy).setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, activity_privacy.class);
            startActivity(intent);
        });
        findViewById(R.id.cardNotifications).setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, activity_notifications.class);
            startActivity(intent);
        });

        // Inside setupCardClicks() in Settings.java

        findViewById(R.id.cardMessages).setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, activity_message_settings.class);
            startActivity(intent);
        });

    }
}