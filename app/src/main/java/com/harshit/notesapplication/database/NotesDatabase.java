package com.harshit.notesapplication.database;

import static com.harshit.notesapplication.utils.Constants.TABLE_NAME;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.harshit.notesapplication.utils.Constants;

@Database(entities = { NotesPojo.class }, version = 2)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao getNotesDao();

    private static NotesDatabase notesDB;

    public static NotesDatabase getInstance(Context context) {
        if (null == notesDB) {
            notesDB = buildDatabaseInstance(context);
        }
        return notesDB;
    }

    private static NotesDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                        NotesDatabase.class,
                        Constants.DB_NAME)
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        notesDB = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE NotesPojo RENAME TO "+ TABLE_NAME);
        }
    };
}
