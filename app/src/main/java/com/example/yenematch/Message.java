package com.example.yenematch;

/**
 * Data model for a single chat message.
 * used by ChatAdapter to determine which bubble to show.
 */
public class Message {
    private String text;
    private boolean isSentByMe;

    // Constructor
    public Message(String text, boolean isSentByMe) {
        this.text = text;
        this.isSentByMe = isSentByMe;
    }

    // Getters
    public String getText() {
        return text;
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }

    // Setters (Useful for updating messages dynamically)
    public void setText(String text) {
        this.text = text;
    }

    public void setSentByMe(boolean sentByMe) {
        isSentByMe = sentByMe;
    }
}