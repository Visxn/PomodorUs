package com.example.pomodorus.Adapters;

public class Message {

    private String userEmail;
    private String message;
    private String dateTime;

    //for firebase getting databack
    public Message() {
    }

    public Message(String userEmai, String message, String dateTime) {
        this.userEmail = userEmai;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getUserEmai() {
        return userEmail;
    }

    public void setUserEmai(String userEmai) {
        this.userEmail = userEmai;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
