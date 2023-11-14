package com.zypher.shortnotes;

import com.google.firebase.firestore.PropertyName;

public class Note {
    @PropertyName("title")
    private String title;

    @PropertyName("content")
    private String content;

    // Document ID in Firestore
    private String documentId;

    // Default constructor without parameters
    public Note() {
        // Required empty constructor for Firebase Firestore
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    // Parameterized constructor
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
