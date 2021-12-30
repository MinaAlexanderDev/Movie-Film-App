package com.example.MyMovieFilmApp.data.locadata.favorite;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

import java.util.List;

public class RepositoryFavorite {
    private FavoriteDao favoriteDao;
    private LiveData<List<MovieModel>> allMovies;
    private LiveData<MovieModel> movieModel;

    public RepositoryFavorite(Application application) {
        FavoriteDatabase favoriteDatabase = FavoriteDatabase.getInstance(application);
        favoriteDao = favoriteDatabase.favoriteDao();
        allMovies = favoriteDao.getAllMovies();
    }

    public void insert(MovieModel favoriteMovies) {
        new InsertMovieAsyncTask(favoriteDao).execute(favoriteMovies);
    }

    public void update(MovieModel favoriteMovies) {
        new updateMovieAsyncTask(favoriteDao).execute(favoriteMovies);
    }

    public void delete(MovieModel favoriteMovies) {
        new deleteMovieAsyncTask(favoriteDao).execute(favoriteMovies);
    }

    public void deleteAllMovies() {
        new deleteAllMovieAsyncTask(favoriteDao).execute();
    }

    public LiveData<List<MovieModel>> getAllMovies() {
        return allMovies;
    }

    public LiveData<MovieModel> getOneMovies(Integer favoriteMovies) {
        return favoriteDao.getOneMovie(favoriteMovies);
     }

    //////////////////////////////

    /////////
    private static class InsertMovieAsyncTask extends AsyncTask<MovieModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private InsertMovieAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(MovieModel... favoriteMovies) {
            favoriteDao.insert(favoriteMovies[0]);
            return null;
        }
    }
    /////////
    private static class updateMovieAsyncTask extends AsyncTask<MovieModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private updateMovieAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(MovieModel... favoriteMovies) {
            favoriteDao.update(favoriteMovies[0]);
            return null;
        }
    }
    /////////
    private static class deleteMovieAsyncTask extends AsyncTask<MovieModel, Void, Void> {
        private FavoriteDao favoriteDao;

        private deleteMovieAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(MovieModel... favoriteMovies) {
            favoriteDao.delete(favoriteMovies[0]);
            return null;
        }
    }
    /////////
    private static class deleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteDao favoriteDao;

        private deleteAllMovieAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDao.deleteAllMovies();
            return null;
        }
    }
}
