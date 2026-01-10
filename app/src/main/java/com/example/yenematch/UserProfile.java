package com.example.yenematch;


public class UserProfile {
    private String full_name;
    private int age;
    private String location;

    private String interests;

    // Getters
    public String getFullName() { return full_name; }
    public int getAge() { return age; }


    // It is good practice to add these as well since they are in your SQL
    public String getLocation() { return location; }
    public String getInterests() { return interests; }
}
