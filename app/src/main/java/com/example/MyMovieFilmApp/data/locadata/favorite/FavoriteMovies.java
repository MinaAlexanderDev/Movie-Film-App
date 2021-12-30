package com.example.MyMovieFilmApp.data.locadata.favorite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "favorite_table")
 public class FavoriteMovies {
    @PrimaryKey
    private int id;

    private String title;
    private String poster_path;
    private String release_date;
    private float vote_average;
    private String overview;
    private int vote_count;

    private List<String> genre_ids;

    public FavoriteMovies(int id, String title, String poster_path, String release_date, float vote_average, String overview, int vote_count, List<String> genre_ids) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.overview = overview;
        this.vote_count = vote_count;
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public List<String> getGenre_ids() {
        return genre_ids;
    }
}
