package com.example.MyMovieFilmApp.ui.fragment.watchnow;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.MyMovieFilmApp.data.getgernres.RepositryGenres;
import com.example.MyMovieFilmApp.data.watchnow.Repositry;
import com.example.MyMovieFilmApp.pojo.genres.Genres;
import com.example.MyMovieFilmApp.pojo.genres.GenresModel;
import com.example.MyMovieFilmApp.pojo.moviedata.Data;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchNowViewModel extends ViewModel {
    private static String TAG = "PostViewModel";
    public MutableLiveData<Data> postsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Genres> GenresMutableLiveData = new MutableLiveData<>();
    private List<MovieModel> Postes = new ArrayList();
    private List<GenresModel> GenresList = new ArrayList();
    private Genres genres = Genres.getINSTANCE();


    public void fillData(String key, int pageNumber) {
        Log.e(TAG, "fillData: " + "key : " + key + " pageNumber : " + String.valueOf(pageNumber));
        Repositry.getINSTANCE().getWatchNowMovies(key, pageNumber).enqueue(new Callback<Data>() {

             @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if (response.isSuccessful() && response.body() != null) {

                    postsMutableLiveData.setValue(response.body());
                    Postes = response.body().getMovies();
                    Log.e(TAG, "total pages : " + response.body().getTotal_pages());

                    for (int i = 0; i < Postes.size(); i++) {
                        Log.e(TAG, "uri : " + Postes.get(i).getPoster_path() + "\n" +
                                "getId : " + Postes.get(i).getId() + "\n" +
                                "getRelease_date : " + Postes.get(i).getRelease_date() +
                                "Vote : " + Postes.get(i).getVote_average());
                    }

                } else {
                    Log.e(TAG, "response.errorBody() : " + response.errorBody());

                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

    }

    public void getGenres(String key) {

        Log.e(TAG, "fillData: " + "key : " + key + " pageNumber : " + String.valueOf(key));
        RepositryGenres.getINSTANCE().getGenres(key).enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GenresMutableLiveData.setValue(response.body());
                    GenresList = response.body().getGenres();
                    genres.setGenres(GenresList);

                    Log.e(TAG, "getGenres 9966774455: " + "Genres.size() : " + genres.getGenres());

                    if (GenresList.size() > 0) {

                        for (int i = 0; i < GenresList.size(); i++) {

                            Log.e(TAG, "getGenres getName : " + GenresList.get(i).getName() + "\n" +
                                    "getId : " + GenresList.get(i).getId());
                        }

                    } else {
                        Log.e(TAG, "getGenres  null size : ");
                    }

                } else {
                    Log.e(TAG, "getGenres response.errorBody() : " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {

            }
        });
    }
}