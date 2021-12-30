package com.example.MyMovieFilmApp.data.search;

import com.example.MyMovieFilmApp.pojo.moviedata.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchInterface {

    @GET("/3/search/movie")
    public Call<Data> getSearchMovies(@Query("api_key") String key,@Query("query") String query,@Query("page") int page);
}
