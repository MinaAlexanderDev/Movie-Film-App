package com.example.MyMovieFilmApp.ui.fragment.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.MyMovieFilmApp.data.locadata.favorite.FavoriteMovies;
import com.example.MyMovieFilmApp.data.locadata.favorite.RepositoryFavorite;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private static String TAG = "FavoriteViewModel";
    private RepositoryFavorite repositoryFavorite;
    private LiveData<List<MovieModel>> allMovies;
    private LiveData<MovieModel> searchMovie;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repositoryFavorite = new RepositoryFavorite(application);
        allMovies = repositoryFavorite.getAllMovies();
    }

    public void insert(MovieModel favoriteMovies) {
        repositoryFavorite.insert(favoriteMovies);
    }

    public void update(MovieModel favoriteMovies) {
        repositoryFavorite.update(favoriteMovies);
    }

    public void delete(MovieModel favoriteMovies) {
        repositoryFavorite.delete(favoriteMovies);
    }

    public LiveData<MovieModel> searchMovies(int id) {
      return  repositoryFavorite.getOneMovies(id);
    }

    public void deleteAll() {
        repositoryFavorite.deleteAllMovies();
    }

    public LiveData<List<MovieModel>> getAllMovies() {
        return allMovies;
    }

}