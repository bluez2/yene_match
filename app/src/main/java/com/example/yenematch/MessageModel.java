package com.example.yenematch;


import com.google.gson.annotations.SerializedName;

public class MessageModel {

    // These names must match your MySQL column names exactly
    private int message_id;
    private int match_id;
    private int sender_id;
    private String message_type;
    private String message_content;
    private String sent_at;
    private int is_read;

    // Constructor for creating a new message locally before sending to server
    public MessageModel(int match_id, int sender_id, String message_type, String message_content) {
        this.match_id = match_id;
        this.sender_id = sender_id;
        this.message_type = message_type;
        this.message_content = message_content;
    }

    // Getters
    public int getMessageId() { return message_id; }
    public int getMatchId() { return match_id; }
    public int getSenderId() { return sender_id; }
    public String getMessageType() { return message_type; }
    public String getMessageContent() { return message_content; }
    public String getSentAt() { return sent_at; }
    public int getIsRead() { return is_read; }
}
