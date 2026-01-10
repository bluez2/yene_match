package com.example.yenematch;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {
    // 1. Add the missing User ID field
    @SerializedName("user_id")
    private int userId;

    private String full_name;
    private int age;
    private String location;
    private String interests;
    private String profile_pic_url;

    // 2. Add the missing Getter for DiscoveryActivity to use
    public int getUserId() {
        return userId;
    }

    public String getFullName() { return full_name; }
    public int getAge() { return age; }
    public String getLocation() { return location; }
    public String getBio() { return interests; }
    public String getProfilePicUrl() { return profile_pic_url; }

    // Setters (Optional but good practice)
    public void setUserId(int userId) { this.userId = userId; }
}