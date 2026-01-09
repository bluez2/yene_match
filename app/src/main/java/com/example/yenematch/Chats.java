package com.example.yenematch;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Chats extends AppCompatActivity {

    private TextView tvLastActive;
    private EditText etMessage;
    private ImageView btnSend, ivMic, ivEmoji, ivImage;

    // RecyclerView variables
    private RecyclerView rvMessages;
    private ChatAdapter adapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        // 1. Initialize UI Views
        tvLastActive = findViewById(R.id.tvLastActive);
        etMessage = findViewById(R.id.etMessage);
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnSend = findViewById(R.id.btnSend);
        ivMic = findViewById(R.id.ivMic);
        ivEmoji = findViewById(R.id.ivEmoji);
        ivImage = findViewById(R.id.ivImage);
        rvMessages = findViewById(R.id.rvMessages);

        // 2. Setup Data and RecyclerView
        messageList = new ArrayList<>();
        setupDummyMessages(); // Pre-load Sarah's chat

        adapter = new ChatAdapter(messageList);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(adapter);

        // Scroll to the latest message
        rvMessages.scrollToPosition(messageList.size() - 1);

        // 3. Last Active Functional Requirement
        tvLastActive.setText("Active 11m ago");

        // 4. Back Button Action
        btnBack.setOnClickListener(v -> finish());

        // 5. TextWatcher for Send Icon Logic
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    btnSend.setVisibility(View.VISIBLE);
                    ivMic.setVisibility(View.GONE);
                } else {
                    btnSend.setVisibility(View.GONE);
                    ivMic.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 6. Send Button Logic
        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (!text.isEmpty()) {
                // Add message to list
                messageList.add(new Message(text, true));

                // Update Adapter
                adapter.notifyItemInserted(messageList.size() - 1);

                // Clear input and scroll to bottom
                etMessage.setText("");
                rvMessages.scrollToPosition(messageList.size() - 1);
            }
        });
    }

    private void setupDummyMessages() {
        // Matches the screenshot you uploaded
        messageList.add(new Message("Hey 👋 I saw your profile and had to say hi", true));
        messageList.add(new Message("Hi", false));
        messageList.add(new Message("So what caught your attention?", false));
        messageList.add(new Message("Honestly? Your smile.", true));
        messageList.add(new Message("And maybe the fact that you like hiking.", true));
        messageList.add(new Message("Hmmm", false));
        messageList.add(new Message("Haha, good answer.", false));
        messageList.add(new Message("So... wanna keep talking?", false));
    }
}