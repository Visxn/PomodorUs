package com.example.pomodorus.Adapters;

public class Session {
    String sessionName, date;
    public Session(){}

    public Session(String sessionName, String date) {
        this.sessionName = sessionName;
        this.date = date;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
