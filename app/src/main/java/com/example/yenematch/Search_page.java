package com.example.yenematch;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Search_page extends AppCompatActivity {

    private EditText searchField;
    private LinearLayout searchResultContainer;
    private TextView resultName, resultBio;
    private List<User> databaseMock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_page);

        // 1. Setup Edge-to-Edge for your specific root ID: main_search_layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_search_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. Initialize Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_search) return true;

            if (id == R.id.nav_discover) {
                startActivity(new Intent(this, DiscoverActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, Profile_page.class));
                overridePendingTransition(0, 0);
                return true;
            }
            else if (id == R.id.nav_messages) {
                startActivity(new Intent(this, Messages.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
        ImageView ivMoreOptions = findViewById(R.id.ivMoreOptions);

        ivMoreOptions.setOnClickListener(v -> {
            // Show the filter pop-up
            dialog_filters filterDialog = new dialog_filters();
            filterDialog.show(getSupportFragmentManager(), "FilterDialog");
        });
        // 3. Initialize Search Views (Ensure these IDs exist in your XML)
        searchField = findViewById(R.id.searchField);
        // Note: You should add android:id="@+id/searchResultContainer" to the result LinearLayout in XML
        searchResultContainer = findViewById(R.id.searchResultContainer);
        resultName = findViewById(R.id.resultName);
        resultBio = findViewById(R.id.resultBio);

        // 4. Mock Database
        databaseMock = new ArrayList<>();
        databaseMock.add(new User("Sarah", "22", "Adventure seeker | Coffee enthusiast"));
        databaseMock.add(new User("Kidus", "24", "Tech lover | Addis Ababa"));

        // 5. Search Icon Click
        findViewById(R.id.ivSearchIcon).setOnClickListener(v -> {
            String query = searchField.getText().toString();
            Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
        });

        // 6. Live Filter Logic
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString().toLowerCase().trim());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            if(searchResultContainer != null) searchResultContainer.setVisibility(View.GONE);
            return;
        }

        for (User user : databaseMock) {
            if (user.name.toLowerCase().contains(query)) {
                if(resultName != null) resultName.setText(user.name + ", " + user.age);
                if(resultBio != null) resultBio.setText(user.bio);
                if(searchResultContainer != null) searchResultContainer.setVisibility(View.VISIBLE);
                return;
            }
        }
        if(searchResultContainer != null) searchResultContainer.setVisibility(View.GONE);
    }

    static class User {
        String name, age, bio;
        User(String n, String a, String b) { this.name = n; this.age = a; this.bio = b; }
    }
}