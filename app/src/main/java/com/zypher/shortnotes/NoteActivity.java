package com.zypher.shortnotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private static final String COLLECTION_NAME = "notes";
    private LinearLayout noteContainer;
    private List<Note> noteList;
    private FirebaseFirestore firestore;
    private CollectionReference notesCollection;

    Button Home_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteContainer = findViewById(R.id.notesContainer);
        Button saveButton = findViewById(R.id.saveButton);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);
        Home_Button = findViewById(R.id.Home_btn);

        Home_Button.setOnClickListener(v -> {
            Log.d("NoteActivity", "Button clicked");
            Intent intent = new Intent(NoteActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        noteList = new ArrayList<>();

        firestore = FirebaseFirestore.getInstance();
        notesCollection = firestore.collection(COLLECTION_NAME);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteAllDialog();
            }
        });

        loadNotesFromFirebase();
        displayNotes();
    }

    private void displayNotes() {
        for (Note note : noteList) {
            createNoteView(note);
        }
    }

    private void loadNotesFromFirebase() {
        notesCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                Note note = documentSnapshot.toObject(Note.class);
                if (note != null) {
                    note.setDocumentId(documentSnapshot.getId());
                    noteList.add(note);
                }
            }
            displayNotes();
        });
    }

    private void saveNote() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);

            noteList.add(note);
            saveNoteToFirebase(note);

            createNoteView(note);
            clearInputFields();
        }
    }

    private void clearInputFields() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    private void createNoteView(final Note note) {
        View noteView = getLayoutInflater().inflate(R.layout.note_item, null);
        TextView titleTextView = noteView.findViewById(R.id.titleTextView);
        TextView contentTextView = noteView.findViewById(R.id.contentTextView);

        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getContent());

        noteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDialog(note);
                return true;
            }
        });
        noteContainer.addView(noteView);
    }

    private void showDeleteDialog(final Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this note.");
        builder.setMessage("이 노트을 삭제 할까요?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteNoteAndRefresh(note);
            }
        });
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void deleteNoteAndRefresh(Note note) {
        // Remove from local list
        noteList.remove(note);

        // Remove from Firebase
        notesCollection.document(note.getDocumentId()).delete();

        // Refresh views
        refreshNoteViews();
    }

    private void showDeleteAllDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all notes.");
        builder.setMessage("모든 노트를 삭제 할까요?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAllNotes();
            }
        });
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void deleteAllNotes() {
        // Remove all notes from local list
        noteList.clear();

        // Remove all notes from Firebase
        notesCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                notesCollection.document(documentSnapshot.getId()).delete();
            }
            // Refresh views
            refreshNoteViews();
        });
    }

    private void refreshNoteViews() {
        noteContainer.removeAllViews();
        displayNotes();
    }

    private void saveNoteToFirebase(Note note) {
        notesCollection.add(note);
    }

    private void saveNotesToFirebase() {
        // Clear existing data in the collection
        notesCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                notesCollection.document(documentSnapshot.getId()).delete();
            }

            // Save the updated list
            for (Note note : noteList) {
                notesCollection.add(note);
            }
        });
    }
}
