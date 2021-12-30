package com.example.MyMovieFilmApp.data.locadata.favorite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

@Database(entities = {MovieModel.class},version =1)
@TypeConverters({Converters.class})
public abstract class FavoriteDatabase extends RoomDatabase {
    private static FavoriteDatabase instance;
    public  abstract FavoriteDao favoriteDao();
    public static synchronized FavoriteDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), FavoriteDatabase.class,"movie_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
