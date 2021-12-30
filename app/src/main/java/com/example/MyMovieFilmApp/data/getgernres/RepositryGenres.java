package com.example.MyMovieFilmApp.data.getgernres;

import com.example.MyMovieFilmApp.pojo.genres.Genres;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositryGenres {

    public static final String BASE_URL = "https://api.themoviedb.org";
    private static RepositryGenres INSTANCE;
    private static String TAG = "RepositryGenres";
    private GenresInterface genresInterface;
    public RepositryGenres() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        genresInterface = retrofit.create(GenresInterface.class);
    }

    public static RepositryGenres getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new RepositryGenres();
        }
        return INSTANCE;
    }

    public Call<Genres> getGenres(String key) {
        return genresInterface.getgenres(key);
    }
}
