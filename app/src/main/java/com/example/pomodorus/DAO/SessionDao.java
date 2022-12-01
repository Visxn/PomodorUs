package com.example.pomodorus.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface SessionDao {

    @Insert
    void insert(Session session);

    @Update
    void update(Session session);

    @Delete
    void delete(Session session);

    //@Query("DELETE FROM sessions_table")
    //void deleteAllNotes();

    //
    // @Query("SELECT * FROM sessions_table ORDER BY priority DESC")
    //LiveData<List<Session>> getAllNotes();

}
