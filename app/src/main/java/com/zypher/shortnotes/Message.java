package com.zypher.shortnotes;

public class Message {
    private String message;
    private String senderId;

    public Message() {
        // Default constructor required for calls to DataSnapshot.getValue(Message.class)
    }

    public Message(String message, String senderId) {
        this.message = message;
        this.senderId = senderId;
    }

    // Getters and setters (or use public fields if you prefer)

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
