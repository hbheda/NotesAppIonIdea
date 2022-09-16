package com.harshit.notesapplication.utils;

import com.harshit.notesapplication.database.NotesPojo;

public abstract class OnItemClickListener {

    public abstract void onItemClick(NotesPojo item, int position);

    public abstract void onItemDeleteClick(NotesPojo item, int position);
}
