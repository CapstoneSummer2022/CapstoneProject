package com.example.electriccomponentsshop.dto;

public class MessageResponse {
    private String messages;

    public MessageResponse() {
    }

    public MessageResponse(String messages) {
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
