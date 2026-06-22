package com.example.quoteoftheday.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

import java.util.List;
import com.example.quoteoftheday.models.Quote;

@Dao
public interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Quote quote);

    @Delete
    void delete(Quote quote);

    @Query("SELECT * FROM favorite_quotes")
    List<Quote> getAllFavorites();

    @Query("SELECT * FROM favorite_quotes WHERE id = :id")
    Quote getQuoteById(int id);
}