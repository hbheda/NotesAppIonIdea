package com.harshit.notesapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.harshit.notesapplication.database.NotesDatabase;
import com.harshit.notesapplication.database.NotesPojo;
import com.harshit.notesapplication.databinding.ActivityAddNoteBinding;
import com.harshit.notesapplication.utils.Validation;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding activityAddNoteBinding;
    private NotesDatabase notesDatabase;
    private NotesPojo notesPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddNoteBinding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        View view = activityAddNoteBinding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_add_note);

        notesPojo = getIntent().getParcelableExtra("note");
        if(notesPojo!=null)
        {
            activityAddNoteBinding.edtTitle.setText(notesPojo.getTitle());
            activityAddNoteBinding.edtDescription.setText(notesPojo.getDescription());
        }

        notesDatabase = NotesDatabase.getInstance(this);

        activityAddNoteBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = activityAddNoteBinding.edtTitle.getText().toString();
                String desc = activityAddNoteBinding.edtDescription.getText().toString();

                boolean valid = true;

                if(Validation.checkStringEmpty(title)) {
                    Toast.makeText(AddNoteActivity.this, "Please enter Title", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if(valid) {
                    if (notesPojo != null) {
                        notesPojo.setTitle(title);
                        notesPojo.setDescription(desc);
                        notesPojo.setDate(new Date());
                        notesDatabase.getNotesDao().update(notesPojo);
                    } else {
                        NotesPojo note = new NotesPojo(title, desc, new Date());
                        notesDatabase.getNotesDao().insert(note);
                    }
                    finish();
                }
            }
        });
    }

}