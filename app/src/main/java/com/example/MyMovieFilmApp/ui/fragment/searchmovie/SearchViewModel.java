package com.example.MyMovieFilmApp.ui.fragment.searchmovie;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.MyMovieFilmApp.data.search.RepositrySearch;
import com.example.MyMovieFilmApp.data.toprate.RepositryTopRate;
import com.example.MyMovieFilmApp.pojo.genres.Genres;
import com.example.MyMovieFilmApp.pojo.genres.GenresModel;
import com.example.MyMovieFilmApp.pojo.moviedata.Data;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private static String TAG = "SearchViewModel";
    public MutableLiveData<Data> postsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Genres> GenresMutableLiveData = new MutableLiveData<>();
    private List<MovieModel> Postes = new ArrayList();
    private List<GenresModel> GenresList = new ArrayList();
    private Genres genres = Genres.getINSTANCE();

    public void fillData(String key,String movename, int pageNumber) {
        Log.e(TAG, "fillData: " + "key : " + key + " pageNumber : " + String.valueOf(pageNumber));
        RepositrySearch.getINSTANCE().getSearchMovies(key,movename, pageNumber).enqueue(new Callback<Data>() {

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
}