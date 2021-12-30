package com.example.MyMovieFilmApp.data.getgernres;

import com.example.MyMovieFilmApp.pojo.genres.Genres;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenresInterface {

    @GET("3/genre/movie/list")
    public Call<Genres> getgenres(@Query("api_key") String key);

}
