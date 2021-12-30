package com.example.MyMovieFilmApp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.MyMovieFilmApp.R;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;
import com.example.MyMovieFilmApp.ui.fragment.favorite.FavoriteFragment;
import com.example.MyMovieFilmApp.ui.fragment.fragmenttoprated.TopRateFragment;
import com.example.MyMovieFilmApp.ui.fragment.moviedetailes.DetailesFragment;
import com.example.MyMovieFilmApp.ui.fragment.searchmovie.SearchFragment;
import com.example.MyMovieFilmApp.ui.fragment.watchnow.WatchNowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements WatchNowFragment.onHeatLisener, TopRateFragment.onHeatLisenerTopRate, SearchFragment.onHeatLisenerSearch,FavoriteFragment.onHeatLisener {

    public static Boolean dual;
    private static String TAG = "MainActivity";
    private DetailesFragment tabletSize;

    private Fragment fragment = null;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView navView;
    boolean doubleBackToExitPressedOnce = false;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new WatchNowFragment();
        switchFragment(fragment);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_watch_now:

                        fragment = new WatchNowFragment();
                        switchFragment(fragment);

                        return true;
                    case R.id.navigation_top_rated:
                        fragment = new TopRateFragment();
                        switchFragment(fragment);
                     return true;
                    case R.id.navigation_search:
                        fragment = new SearchFragment();
                        switchFragment(fragment);
                        return true;
                    case R.id.navigation_favorite:
                        fragment = new FavoriteFragment();
                        switchFragment(fragment);
                        return true;
                }

                return false;

            }
        });


        tabletSize = (DetailesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detailes_large);
        if (tabletSize == null) {
//            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//            NavigationUI.setupWithNavController(navView, navController);
        } else {

        }

    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);

        fragmentTransaction.commit();
        manager.popBackStack();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
////                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
    }

    @Override
    public void heating(MovieModel position,int page) {
        Log.e(TAG, "heating move id: " + position);
        Log.e(TAG, "heating move id: " + position.getId());
        Log.e(TAG, "heating move id: " + position.getPoster_path());
        Log.e(TAG, "heating move id: " + position.getRelease_date());


        if (tabletSize == null) {
            dual = false;
            Log.e(TAG, "heating: tabletSize : true " + position);
            Intent intent = new Intent(this, DetailesActivity.class);
            intent.putExtra("movie", position);
            intent.putExtra("id", position.getId());
            intent.putExtra("page",page);
             startActivity(intent);
        } else {
            dual = true;

            tabletSize.update_Info(position,page);

        }
    }

}
