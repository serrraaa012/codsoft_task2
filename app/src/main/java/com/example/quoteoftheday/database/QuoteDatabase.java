package com.example.quoteoftheday.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.quoteoftheday.models.Quote;

@Database(entities = {Quote.class}, version = 1)
public abstract class QuoteDatabase extends RoomDatabase {
    private static QuoteDatabase instance;

    public abstract QuoteDao quoteDao();

    public static synchronized QuoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteDatabase.class, "quote_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}