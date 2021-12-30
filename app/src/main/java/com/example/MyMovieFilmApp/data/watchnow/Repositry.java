package com.example.MyMovieFilmApp.data.watchnow;

import com.example.MyMovieFilmApp.pojo.moviedata.Data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repositry {

    public static final String BASE_URL = "https://api.themoviedb.org";
    private static Repositry INSTANCE;
    private static String TAG = "Repositry";
    private WatchNowInterface watchNowInterface;

    public Repositry() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        watchNowInterface = retrofit.create(WatchNowInterface.class);
    }

    public static Repositry getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new Repositry();
        }
        return INSTANCE;
    }

    public Call<Data> getWatchNowMovies(String key, int pageNumber) {
        return watchNowInterface.getPosts(key, pageNumber);
    }
}
