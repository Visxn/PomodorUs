package com.example.pomodorus.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pomodorus.DAO.Session;
import com.example.pomodorus.DAO.SessionDao;

@Database(entities = {Session.class}, version = 1)
public abstract class SessionDatabase extends RoomDatabase {

    private static SessionDatabase instance;
    public abstract SessionDao SessionDao();

    public static synchronized SessionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SessionDatabase.class, "session_database")
                   .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
