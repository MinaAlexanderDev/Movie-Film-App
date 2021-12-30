package com.example.MyMovieFilmApp.pojo.moviedata;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "favorite_table")
public class MovieModel implements Parcelable {

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
    //    @PrimaryKey(autoGenerate = true)
//    @PrimaryKey()
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String poster_path;
    private String release_date;
    private float vote_average;
    private String overview;
    private int vote_count;
    private List<String> genre_ids;

    @Ignore
    public MovieModel(int id, String title, String poster_path, String release_date, float vote_average, String overview, int vote_count, List<String> genre_ids) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.overview = overview;
        this.vote_count = vote_count;
        this.genre_ids = genre_ids;
    }

    public MovieModel(String title, String poster_path, String release_date, float vote_average, String overview, int vote_count, List<String> genre_ids) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.overview = overview;
        this.vote_count = vote_count;
        this.genre_ids = genre_ids;
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        vote_average = in.readFloat();
        overview = in.readString();
        vote_count = in.readInt();
//        getGenre_ids()=in.createTypedArrayList();
        genre_ids = in.createStringArrayList();

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public List<String> getGenre_ids() {
        return genre_ids;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeFloat(vote_average);
        parcel.writeString(overview);
        parcel.writeInt(vote_count);
//        parcel.writeList(genre_ids);
        parcel.writeStringList(genre_ids);
    }
//Constructor
//    public MovieModel(int id, String title, String poster_path, String release_date, float vote_average) {
//        this.id = id;
//        this.title = title;
//        this.poster_path = poster_path;
//        this.release_date = release_date;
//        this.vote_average = vote_average;
//    }
//
//    //Getters
//    protected MovieModel(Parcel in) {
//        id = in.readInt();
//        title = in.readString();
//        poster_path = in.readString();
//        release_date = in.readString();
//        vote_average = in.readFloat();
//    }
//
//    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
//        @Override
//        public MovieModel createFromParcel(Parcel in) {
//            return new MovieModel(in);
//        }
//
//        @Override
//        public MovieModel[] newArray(int size) {
//            return new MovieModel[size];
//        }
//    };
//
//    public int getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getPoster_path() {
//        return poster_path;
//    }
//
//    public String getRelease_date() {
//        return release_date;
//    }
//
//    public float getVote_average() {
//        return vote_average;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(title);
//        dest.writeString(poster_path);
//        dest.writeString(release_date);
//        dest.writeFloat(vote_average);
//    }
//

}
