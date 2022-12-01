package com.example.pomodorus.DAO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sessions_table")
public class Session {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String date;

    public Session(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
