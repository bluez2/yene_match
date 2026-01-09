package com.example.yenematch;

import android.content.Intent; // Needed for navigation
import android.os.Bundle;
import android.widget.Button;   // Needed for Edit Profile button
import android.widget.ImageView; // Needed for Settings gear icon
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        // System Bar Padding (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Settings Icon Functionality
        ImageView settingsBtn = findViewById(R.id.settings_gear); // Make sure this ID matches your XML
        settingsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Profile_page.this, Settings.class);
            startActivity(intent);
        });

        // 2. Edit Profile Button Functionality
        Button editProfileBtn = findViewById(R.id.btnEditProfile); // Make sure this ID matches your XML
        editProfileBtn.setOnClickListener(v -> {
            editProfile dialog = new editProfile();
            dialog.show(getSupportFragmentManager(), "EditProfileDialog");
            // If you have an EditProfile activity, navigate there:
            // Intent intent = new Intent(Profile_page.this, EditProfileActivity.class);
            // startActivity(intent);
        });
        // Inside onCreate method
        TextView appName = findViewById(R.id.appName);

        appName.setOnClickListener(v -> {
            // Intent to navigate to DiscoverActivity
            Intent intent = new Intent(Profile_page.this, DiscoverActivity.class);
            startActivity(intent);
        });
    }
}