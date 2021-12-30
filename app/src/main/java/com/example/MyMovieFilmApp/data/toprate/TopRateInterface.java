package com.example.MyMovieFilmApp.data.toprate;

import com.example.MyMovieFilmApp.pojo.moviedata.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopRateInterface {

    @GET("/3/movie/top_rated")
    public Call<Data> getPosts(@Query("api_key") String key, @Query("page") int page);
}
