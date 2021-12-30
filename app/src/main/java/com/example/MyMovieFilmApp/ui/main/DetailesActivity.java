package com.example.MyMovieFilmApp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MyMovieFilmApp.R;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;
import com.example.MyMovieFilmApp.ui.fragment.moviedetailes.DetailesFragment;

@VisibleForTesting
public class DetailesActivity extends AppCompatActivity {
    private static final String TAG = "DetailesActivity";
    DetailesFragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        Intent intent = getIntent();


        currentFragment = (DetailesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detailes);
        movie_Info();
    }

    public void movie_Info() {
        if (getIntent().hasExtra("movie")) {
            Log.e(TAG, "data heating postion update_Info : movieModel " + getIntent().hasExtra("movie"));
            Log.e(TAG, "data heating postion update_Info : movieModel " + getIntent().getParcelableExtra("movie"));
            Log.e(TAG, "data heating postion update_Info : movieModel id " + getIntent().getIntExtra("id", 55));
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            int page=getIntent().getIntExtra("page",0);
            Log.e(TAG, "data heating postion update_Info : getId " + movieModel.getId());
            Log.e(TAG, "data heating postion update_Info : getVote_average " + movieModel.getVote_average());
            Log.e(TAG, "data heating postion update_Info : getVote_count " + movieModel.getVote_count());
            Log.e(TAG, "data heating postion update_Info : getGenreIds " + movieModel.getGenre_ids());
            currentFragment.update_Info(movieModel,page);

        }

    }

}