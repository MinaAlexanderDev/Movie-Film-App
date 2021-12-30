package com.example.MyMovieFilmApp.data.search;

import com.example.MyMovieFilmApp.pojo.moviedata.Data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositrySearch {

    public static final String BASE_URL = "https://api.themoviedb.org";
    private static RepositrySearch INSTANCE;
    private static String TAG = "RepositryTopRate";
    private SearchInterface movieInterface;

    public RepositrySearch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieInterface = retrofit.create(SearchInterface.class);
    }

    public static RepositrySearch getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new RepositrySearch();
        }
        return INSTANCE;
    }

    public Call<Data> getSearchMovies(String key, String movename,int pageNumber) {
        return movieInterface.getSearchMovies(key, movename,pageNumber);
    }
}
