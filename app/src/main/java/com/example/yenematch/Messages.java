package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // 1. Initialize Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 2. Set "Messages" as Selected (Turns it RED)
        bottomNavigationView.setSelectedItemId(R.id.nav_messages);

        // 3. Setup Navigation Click Listeners
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_messages) {
                return true; // Already here
            } else if (id == R.id.nav_discover) {
                startActivity(new Intent(Messages.this, DiscoverActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(Messages.this, Search_page.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, Profile_page.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });

        // 4. Click listener for Sarah (Example for opening a chat)
        LinearLayout itemSarah = findViewById(R.id.message_item_sarah);
        itemSarah.setOnClickListener(v -> {
            Intent intent = new Intent(Messages.this, Chats.class);
            startActivity(intent);
        });
    }
}