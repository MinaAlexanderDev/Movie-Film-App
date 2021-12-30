package com.example.MyMovieFilmApp.pojo.moviedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("total_pages")
    @Expose()
    private int total_pages;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    //    private ArrayList<Results> results;
    //
    @Override
    public String toString() {
        return "MoviesSearchResponses{" +
                "total_pages=" + total_pages +
                ", movies=" + movies +
                '}';
    }
//    @SerializedName("uri")
//    @Expose
//    private String uri;
//    @SerializedName("url")
//    @Expose
//    private String url;
//    @SerializedName("id")
//    @Expose
//    private Long id;
//    @SerializedName("assetId")
//    @Expose
//    private Long assetId;
//    @SerializedName("source")
//    @Expose
//    private String source;
//    @SerializedName("title")
//    @Expose
//    private String title;
//
//    @SerializedName("abstract")
//    @Expose
//    private String Articalbody;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getArticalbody() {
//        return Articalbody;
//    }
//
//    public void setArticalbody(String articalbody) {
//        Articalbody = articalbody;
//    }
//
//    public String getUri() {
//        return uri;
//    }
//
//    public void setUri(String uri) {
//        this.uri = uri;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getAssetId() {
//        return assetId;
//    }
//
//    public void setAssetId(Long assetId) {
//        this.assetId = assetId;
//    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
}
