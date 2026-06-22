package com.example.quoteoftheday;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quoteoftheday.adapters.QuoteAdapter;
import com.example.quoteoftheday.database.QuoteDatabase;
import com.example.quoteoftheday.models.Quote;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private QuoteAdapter adapter;
    private List<Quote> favoriteQuotes;
    private QuoteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        initializeViews();
        loadFavorites();
    }

    private void initializeViews() {
        RecyclerView recyclerView = findViewById(R.id.favoritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoriteQuotes = new ArrayList<>();
        adapter = new QuoteAdapter(favoriteQuotes, this::removeFavorite);
        recyclerView.setAdapter(adapter);
        database = QuoteDatabase.getInstance(this);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadFavorites() {
        new Thread(() -> {
            favoriteQuotes.clear();
            favoriteQuotes.addAll(database.quoteDao().getAllFavorites());
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                if (favoriteQuotes.isEmpty()) {
                    Toast.makeText(this, "No favorite quotes yet!", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void removeFavorite(Quote quote) {
        new Thread(() -> {
            database.quoteDao().delete(quote);
            runOnUiThread(() -> {
                favoriteQuotes.remove(quote);
                adapter.notifyDataSetChanged();
                Toast.makeText(FavoritesActivity.this,
                        "Quote removed from favorites", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }
}