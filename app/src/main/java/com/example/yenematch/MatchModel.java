package com.example.yenematch;

public class MatchModel {
    private int match_id;
    private int other_user_id;
    private String full_name;
    private String last_message;
    private String profile_pic_url;

    // Getters
    public int getMatchId() { return match_id; }
    public String getFullName() { return full_name; }
    public String getLastMessage() { return last_message; }
}
