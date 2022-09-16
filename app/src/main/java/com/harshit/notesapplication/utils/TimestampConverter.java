package com.harshit.notesapplication.utils;

import androidx.room.TypeConverter;

import java.util.Date;

public class TimestampConverter {

    @TypeConverter
    public Date fromTimestamp(long value) {
        return new Date(value);
    }

    @TypeConverter
    public long dateToTimestamp(Date date) {
        return date.getTime();
    }
}
