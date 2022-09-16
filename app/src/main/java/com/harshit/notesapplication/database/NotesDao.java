package com.harshit.notesapplication.database;

import static com.harshit.notesapplication.utils.Constants.TABLE_NAME;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY date DESC")
    List<NotesPojo> getAllNotes();

    @Insert
    void insert(NotesPojo note);

    @Update
    void update(NotesPojo repos);

    @Delete
    void delete(NotesPojo note);

    @Delete
    void delete(NotesPojo... note);

}

