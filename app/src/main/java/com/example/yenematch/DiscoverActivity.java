package com.example.yenematch;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

public class DiscoverActivity extends AppCompatActivity {

    private TextView tvNameAge, tvBio;
    private ImageView ivProfile;
    private BottomNavigationView bottomNavigationView;

    // 1. Updated Data Arrays (Added Image Array)
    private String[] names = {"Sarah, 22", "Abeba, 24", "Marta, 21", "Selam, 23"};
    private String[] bios = {
            "Adventure seeker | Coffee enthusiast | Love hiking",
            "Art lover and bookworm. Let's go to a gallery!",
            "Architecture student. I love exploring the city.",
            "Fitness enthusiast and foodie. Looking for a partner!"
    };

    // This array holds the IDs of the images you put in the drawable folder
    private int[] profileImages = {
            R.drawable.img_user1, // Sarah's photo
            R.drawable.img_user2, // Abeba's photo
            R.drawable.img_user3, // Marta's photo
            R.drawable.img_user4  // Selam's photo
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        tvNameAge = findViewById(R.id.tvNameAge);
        tvBio = findViewById(R.id.tvBio);
        ivProfile = findViewById(R.id.ivProfile);
        ImageButton btnLike = findViewById(R.id.btnLike);
        ImageButton btnDislike = findViewById(R.id.btnDislike);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        updateProfile();

        btnLike.setOnClickListener(v -> {
            Toast.makeText(this, "Liked!", Toast.LENGTH_SHORT).show();
            showNextProfile();
        });

        btnDislike.setOnClickListener(v -> {
            Toast.makeText(this, "Skipped!", Toast.LENGTH_SHORT).show();
            showNextProfile();
        });

        setupBottomNavigation();
    }

    private void showNextProfile() {
        currentIndex++;
        if (currentIndex >= names.length) {
            currentIndex = 0; // Loop back to start
        }
        updateProfile();
    }

    private void updateProfile() {
        // 2. This now updates Text AND Image
        tvNameAge.setText(names[currentIndex]);
        tvBio.setText(bios[currentIndex]);
        ivProfile.setImageResource(profileImages[currentIndex]);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_discover);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_discover) return true;
            else if (id == R.id.nav_search) {
                Intent intent = new Intent(DiscoverActivity.this, Search_page.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            // ... (rest of your navigation logic)
            return true;
        });
    }
}