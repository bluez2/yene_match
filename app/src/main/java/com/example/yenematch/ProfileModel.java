package com.example.yenematch;

public class ProfileModel {
    private String full_name;
    private int age;
    private String location;
    private String interests; // We can use this for the bio
    private String profile_pic_url;

    // Getters
    public String getFullName() { return full_name; }
    public int getAge() { return age; }
    public String getLocation() { return location; }
    public String getBio() { return interests; }
}
