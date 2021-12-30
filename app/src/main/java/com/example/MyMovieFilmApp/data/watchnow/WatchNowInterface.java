package com.example.MyMovieFilmApp.data.watchnow;

import com.example.MyMovieFilmApp.pojo.moviedata.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WatchNowInterface {

    @GET("3/movie/now_playing")
    public Call<Data> getPosts(@Query("api_key") String key, @Query("page") int page);

}
