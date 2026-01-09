package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        // 1. Initialize the Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 2. Highlight "Search" as the Active/Selected item (Turns it RED)
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        // 3. Handle Navigation Clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_search) {
                // We are already on the Search page, so do nothing but stay here
                return true;
            }
            else if (id == R.id.nav_discover) {
                // Go back to the Discover (Sarah/Swiping) screen
                Intent intent = new Intent(Search_page.this, DiscoverActivity.class);
                startActivity(intent);
                // overridePendingTransition(0,0) makes the screen swap instantly without sliding
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            else if (id == R.id.nav_messages) {
                Toast.makeText(this, "Messages Screen coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Profile Screen coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        // 4. Setup Search Icon Logic (Inside the Search Bar)
        findViewById(R.id.ivSearchIcon).setOnClickListener(v -> {
            Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
        });
    }
}