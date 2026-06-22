package com.example.quoteoftheday.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_quotes")
public class Quote {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String text;
    private final String author;
    private boolean isFavorite;

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
        this.isFavorite = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }

    public String getAuthor() { return author; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}