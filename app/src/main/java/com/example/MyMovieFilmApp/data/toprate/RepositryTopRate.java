package com.example.MyMovieFilmApp.data.toprate;

import com.example.MyMovieFilmApp.pojo.moviedata.Data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositryTopRate {

    public static final String BASE_URL = "https://api.themoviedb.org";
    private static RepositryTopRate INSTANCE;
    private static String TAG = "RepositryTopRate";
    private TopRateInterface movieInterface;

    public RepositryTopRate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieInterface = retrofit.create(TopRateInterface.class);
    }

    public static RepositryTopRate getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new RepositryTopRate();
        }
        return INSTANCE;
    }

    public Call<Data> getTopRateMovies(String key, int pageNumber) {
        return movieInterface.getPosts(key, pageNumber);
    }
}
