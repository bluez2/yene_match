package com.example.yenematch;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chats extends AppCompatActivity {

    private TextView tvUserName, tvLastActive;
    private EditText etMessage;
    private ImageView btnSend, ivMic;
    private RecyclerView rvMessages;
    private ChatAdapter adapter;
    private List<MessageModel> messageList = new ArrayList<>();

    private int matchId;
    private int currentUserId;
    private String otherUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        // 1. Get Data from Intent
        matchId = getIntent().getIntExtra("MATCH_ID", -1);
        currentUserId = getIntent().getIntExtra("LOGGED_IN_USER_ID", -1);
        otherUserName = getIntent().getStringExtra("OTHER_USER_NAME");

        // 2. Initialize UI
        tvUserName = findViewById(R.id.tvUserName);
        tvLastActive = findViewById(R.id.tvLastActive);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        ivMic = findViewById(R.id.ivMic);
        rvMessages = findViewById(R.id.rvMessages);
        ImageButton btnBack = findViewById(R.id.btnBack);

        tvUserName.setText(otherUserName);
        btnBack.setOnClickListener(v -> finish());

        // 3. Setup RecyclerView
        adapter = new ChatAdapter(messageList, currentUserId);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(adapter);

        // 4. Load Real Messages
        loadChatHistory();

        // 5. Send Message Logic
        btnSend.setOnClickListener(v -> sendMessage());

        setupTextWatcher();
    }

    private void loadChatHistory() {
        RetroFitClient.getInterface().getChatMessages(matchId).enqueue(new Callback<List<MessageModel>>() {
            @Override
            public void onResponse(Call<List<MessageModel>> call, Response<List<MessageModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messageList.clear();
                    messageList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    rvMessages.scrollToPosition(messageList.size() - 1);
                }
            }

            @Override
            public void onFailure(Call<List<MessageModel>> call, Throwable t) {
                Toast.makeText(Chats.this, "Failed to load chat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage() {
        String content = etMessage.getText().toString().trim();
        if (content.isEmpty()) return;

        // Immediately update UI for better UX
        MessageModel newMessage = new MessageModel(matchId, currentUserId, "text", content);
        messageList.add(newMessage);
        adapter.notifyItemInserted(messageList.size() - 1);
        rvMessages.scrollToPosition(messageList.size() - 1);
        etMessage.setText("");

        // Save to Database
        RetroFitClient.getInterface().sendChatMessage(matchId, currentUserId, "text", content)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(Chats.this, "Message failed to send", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("CHAT_ERROR", t.getMessage());
                    }
                });
    }

    private void setupTextWatcher() {
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasText = s.toString().trim().length() > 0;
                btnSend.setVisibility(hasText ? View.VISIBLE : View.GONE);
                ivMic.setVisibility(hasText ? View.GONE : View.VISIBLE);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}