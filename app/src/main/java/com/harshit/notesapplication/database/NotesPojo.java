package com.harshit.notesapplication.database;

import static com.harshit.notesapplication.utils.Constants.TABLE_NAME;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.harshit.notesapplication.utils.TimestampConverter;

import java.util.Date;

@Entity (tableName = TABLE_NAME)
public class NotesPojo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;

    @TypeConverters(TimestampConverter.class)
    private Date date;

    public NotesPojo(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    protected NotesPojo(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<NotesPojo> CREATOR = new Creator<NotesPojo>() {
        @Override
        public NotesPojo createFromParcel(Parcel in) {
            return new NotesPojo(in);
        }

        @Override
        public NotesPojo[] newArray(int size) {
            return new NotesPojo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
