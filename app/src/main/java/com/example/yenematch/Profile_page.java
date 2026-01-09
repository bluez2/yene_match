package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // 1. Initialize Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 2. Highlight "Profile" as Active (Turns RED)
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        // 3. Navigation Click Listeners
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                return true; // Already here
            } else if (id == R.id.nav_discover) {
                startActivity(new Intent(Profile_page.this, DiscoverActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(Profile_page.this, Search_page.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_messages) {
                startActivity(new Intent(Profile_page.this, Messages.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });

        // 4. Settings Icon Functionality
        ImageView settingsBtn = findViewById(R.id.settings_gear);
        settingsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Profile_page.this, Settings.class);
            startActivity(intent);
        });

        // 5. Edit Profile Button Functionality
        Button editProfileBtn = findViewById(R.id.btnEditProfile);
        editProfileBtn.setOnClickListener(v -> {
            // Using a Toast for now as a placeholder for your dialog logic
            Toast.makeText(this, "Opening Edit Profile...", Toast.LENGTH_SHORT).show();
            // editProfile dialog = new editProfile();
            // dialog.show(getSupportFragmentManager(), "EditProfileDialog");
        });

        // 6. App Name Click (Shortcut to Discover)
        TextView appName = findViewById(R.id.appName);
        appName.setOnClickListener(v -> {
            Intent intent = new Intent(Profile_page.this, DiscoverActivity.class);
            startActivity(intent);
            finish();
        });
    }
}