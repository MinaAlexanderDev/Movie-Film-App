package com.example.MyMovieFilmApp.data.locadata.favorite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(MovieModel favoriteMovies);

    @Update
    void update(MovieModel favoriteMovies);

    @Delete
    void delete(MovieModel favoriteMovies);

    //delete all movies one time
    @Query("DELETE FROM favorite_table")
    void deleteAllMovies();

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    LiveData<MovieModel> getOneMovie(int id);

    @Query("SELECT *FROM favorite_table ORDER BY id DESC")
    LiveData<List<MovieModel>> getAllMovies();

}
