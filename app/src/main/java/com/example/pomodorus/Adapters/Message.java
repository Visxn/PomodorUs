package com.example.pomodorus.Adapters;

import androidx.recyclerview.widget.RecyclerView;

public class Message  {
    public String userEmail;
    public String message;
    public String dateTime;


    //for Firebase getting data back
    public Message() {
    }

    public Message(String userEmail, String message, String dateTime) {
        this.userEmail = userEmail;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public void setDayTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
