package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Messages extends AppCompatActivity {

    private RecyclerView rvMatches;
    private MatchAdapter adapter;
    private List<MatchModel> matchList = new ArrayList<>();
    private int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // 1. Get the dynamic ID from the Intent
        currentUserId = getIntent().getIntExtra("LOGGED_IN_USER_ID", -1);
        if (currentUserId == -1) {
            finish(); // Safety exit
        }

        // 2. Initialize UI
        rvMatches = findViewById(R.id.rvMatches); // Make sure this ID exists in your XML
        rvMatches.setLayoutManager(new LinearLayoutManager(this));

        // 3. Load Matches from Database
        loadMatches();

        // 4. Setup Bottom Navigation
        setupBottomNavigation();
    }

    private void loadMatches() {
        RetroFitClient.getInterface().getMyMatches(currentUserId).enqueue(new Callback<List<MatchModel>>() {
            @Override
            public void onResponse(Call<List<MatchModel>> call, Response<List<MatchModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    matchList = response.body();

                    // Setup Adapter with a click listener to open the specific chat
                    adapter = new MatchAdapter(matchList, match -> {
                        Intent intent = new Intent(Messages.this, Chats.class);
                        intent.putExtra("MATCH_ID", match.getMatchId());
                        intent.putExtra("OTHER_USER_NAME", match.getFullName());
                        intent.putExtra("LOGGED_IN_USER_ID", currentUserId);
                        startActivity(intent);
                    });
                    rvMatches.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<MatchModel>> call, Throwable t) {
                Toast.makeText(Messages.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_messages);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_messages) return true;

            Intent intent = null;
            if (id == R.id.nav_discover) intent = new Intent(this, DiscoverActivity.class);
            else if (id == R.id.nav_search) intent = new Intent(this, Search_page.class);
            else if (id == R.id.nav_profile) intent = new Intent(this, Profile_page.class);

            if (intent != null) {
                intent.putExtra("LOGGED_IN_USER_ID", currentUserId); // Pass ID forward
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
    }
}