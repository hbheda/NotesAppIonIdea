package com.harshit.notesapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.harshit.notesapplication.database.NotesDatabase;
import com.harshit.notesapplication.adapter.NotesListAdapter;
import com.harshit.notesapplication.database.NotesPojo;
import com.harshit.notesapplication.utils.OnItemClickListener;
import com.harshit.notesapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private NotesDatabase notesDatabase;
    private List<NotesPojo> notesPojoList = new ArrayList<>();
    private NotesListAdapter notesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);

        notesDatabase = NotesDatabase.getInstance(this);

        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(NotesPojo note, int position) {
                Intent addIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                addIntent.putExtra("note", note);
                startActivity(addIntent);
            }

            @Override
            public void onItemDeleteClick(NotesPojo note, int position) {
                notesDatabase.getNotesDao().delete(note);
                notesPojoList = notesDatabase.getNotesDao().getAllNotes();
                notesListAdapter.sortList(notesPojoList);
                if(notesPojoList.isEmpty())
                    activityMainBinding.txtNoNote.setVisibility(View.VISIBLE);
            }
        };

        notesListAdapter = new NotesListAdapter(notesPojoList, this, onItemClickListener);
        activityMainBinding.rvNotes.setAdapter(notesListAdapter);
        activityMainBinding.rvNotes.setLayoutManager(new LinearLayoutManager(this));

        activityMainBinding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addIntent);
                //String result = new GetNotes(this).execute();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        notesPojoList = notesDatabase.getNotesDao().getAllNotes();
        if(notesPojoList.isEmpty())
            activityMainBinding.txtNoNote.setVisibility(View.VISIBLE);
        if(notesListAdapter!=null) notesListAdapter.sortList(notesPojoList);
    }
}