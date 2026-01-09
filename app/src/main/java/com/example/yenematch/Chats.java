package com.example.yenematch;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Chats extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chats);

        // Handle system bar insets (status bar/navigation bar padding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Initialize the RecyclerView
        recyclerView = findViewById(R.id.chatRecyclerView);

        // 2. Prepare the data (Matching your Sarah screenshot)
        messages = new ArrayList<>();
        messages.add(new Message("Hey 👋 I saw your profile and had to say hi", true));
        messages.add(new Message("Hi", false));
        messages.add(new Message("So what caught your attention?", false));
        messages.add(new Message("Honestly? Your smile.", true));
        messages.add(new Message("And maybe the fact that you like hiking.", true));
        messages.add(new Message("Hmmm", false));
        messages.add(new Message("Haha, good answer.", false));
        messages.add(new Message("So... wanna keep talking?", false));

        // 3. Set up the Adapter
        adapter = new ChatAdapter(messages);

        // 4. Configure LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // This makes the chat feel natural by starting the list from the bottom
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}