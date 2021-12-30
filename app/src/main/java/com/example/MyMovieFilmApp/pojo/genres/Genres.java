package com.example.MyMovieFilmApp.pojo.genres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Genres {


    private static Genres INSTANCE = null;
    @SerializedName("genres")
    @Expose()
    private List<GenresModel> genres;
    private Map<Integer, String> additionalProperties = new HashMap<Integer, String>();

    public static Genres getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new Genres();
        }
        return INSTANCE;
    }

    public List<GenresModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresModel> genres) {
        this.genres = genres;
    }

    public Map<Integer, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<Integer, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "Genres{" +
                "genres=" + genres +
                '}';
    }

}
