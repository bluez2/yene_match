package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverActivity extends AppCompatActivity {

    private TextView tvNameAge, tvBio, tvLocation;
    private ImageView ivProfile;
    private BottomNavigationView bottomNavigationView;

    private List<ProfileModel> profileList = new ArrayList<>();
    private int currentIndex = 0;

    // DYNAMIC ID: Retrieved from Intent
    private int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        // 1. Get the dynamic ID passed during Login
        currentUserId = getIntent().getIntExtra("LOGGED_IN_USER_ID", -1);

        if (currentUserId == -1) {
            // Error handling: if no ID, go back to login
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        // Initialize Views
        tvNameAge = findViewById(R.id.tvNameAge);
        tvBio = findViewById(R.id.tvBio);
        tvLocation = findViewById(R.id.tvLocation);
        ivProfile = findViewById(R.id.ivProfile);
        ImageButton btnLike = findViewById(R.id.btnLike);
        ImageButton btnDislike = findViewById(R.id.btnDislike);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        loadProfilesFromDatabase();

        ImageView btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, Settings.class)));

        // 2. Button Functionality
        btnLike.setOnClickListener(v -> handleSwipe("like"));
        btnDislike.setOnClickListener(v -> handleSwipe("dislike"));

        setupBottomNavigation();
    }

    private void loadProfilesFromDatabase() {
        RetroFitClient.getInterface().getAllProfiles().enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    profileList = response.body();
                    // Optional: Filter out the current user from the list
                    cleanProfileList();
                    if (!profileList.isEmpty()) {
                        updateProfileUI();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {
                Toast.makeText(DiscoverActivity.this, "Failed to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cleanProfileList() {
        // Remove yourself from the discovery feed
        for (int i = 0; i < profileList.size(); i++) {
            if (profileList.get(i).getUserId() == currentUserId) {
                profileList.remove(i);
                break;
            }
        }
    }

    private void handleSwipe(String status) {
        if (profileList.isEmpty()) return;

        int targetUserId = profileList.get(currentIndex).getUserId();

        // 3. Record in MySQL Table (liker_id, liked_id, status)
        RetroFitClient.getInterface().recordInteraction(currentUserId, targetUserId, status)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            showNextProfile();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DiscoverActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showNextProfile() {
        currentIndex++;
        if (currentIndex >= profileList.size()) {
            currentIndex = 0; // Loop back
            Toast.makeText(this, "Refreshing list...", Toast.LENGTH_SHORT).show();
        }
        updateProfileUI();
    }

    private void updateProfileUI() {
        ProfileModel currentProfile = profileList.get(currentIndex);
        tvNameAge.setText(currentProfile.getFullName() + ", " + currentProfile.getAge());
        tvBio.setText(currentProfile.getBio());
        tvLocation.setText("📍 " + currentProfile.getLocation());

        // Placeholder for image
        ivProfile.setImageResource(R.drawable.img_user1);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_discover);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_discover) return true;

            Intent intent = null;
            if (id == R.id.nav_search) intent = new Intent(this, Search_page.class);
            else if (id == R.id.nav_messages) intent = new Intent(this, Messages.class);
            else if (id == R.id.nav_profile) intent = new Intent(this, Profile_page.class);

            if (intent != null) {
                intent.putExtra("LOGGED_IN_USER_ID", currentUserId); // Keep the ID moving!
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
    }
}