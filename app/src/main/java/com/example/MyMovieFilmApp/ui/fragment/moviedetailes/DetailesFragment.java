package com.example.MyMovieFilmApp.ui.fragment.moviedetailes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.MyMovieFilmApp.R;
import com.example.MyMovieFilmApp.pojo.genres.Genres;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;
import com.example.MyMovieFilmApp.ui.fragment.favorite.FavoriteViewModel;
import com.example.MyMovieFilmApp.ui.fragment.watchnow.WatchNowFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Fragment_Detailes";
    private TextView movieTitle, movieDescription, movieVoteCount, movieGernres;
    private ImageView movieImage;
    private RatingBar movieRate;
    private Genres genres;
    private ArrayList<String> GenresTypes = new ArrayList<>();
    private FavoriteViewModel favoriteViewModel;
    private ToggleButton favoriteToggle;
    private MovieModel favoritemovieModel;
    private boolean checkInDatabase = true;
    //    private FragmentDetailesBinding binding;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailesFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static DetailesFragment newInstance(String param1, String param2) {
        DetailesFragment fragment = new DetailesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_detaile, container, false);
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        genres = Genres.getINSTANCE();
        movieImage = view.findViewById(R.id.movieImage);
        movieTitle = view.findViewById(R.id.Movie_title);
        movieDescription = view.findViewById(R.id.move_overview);
        movieVoteCount = view.findViewById(R.id.move_vote);
        movieGernres = view.findViewById(R.id.moive_gernres);
        movieRate = view.findViewById(R.id.movie_ratingBar);
        favoriteToggle = view.findViewById(R.id.favButton);
        favoriteToggle.setClickable(true);
        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    if (!checkInDatabase) {
                        MovieModel favoriteMovies = new MovieModel(favoritemovieModel.getId(), favoritemovieModel.getTitle(), favoritemovieModel.getPoster_path(), favoritemovieModel.getRelease_date(), favoritemovieModel.getVote_average(), favoritemovieModel.getOverview(), favoritemovieModel.getVote_count(), favoritemovieModel.getGenre_ids());
                        favoriteViewModel.insert(favoriteMovies);
                    } else {

                    }
                } else {
                    if (checkInDatabase) {
                        if (favoritemovieModel != null) {
                            MovieModel favoriteMovies = new MovieModel(favoritemovieModel.getId(), favoritemovieModel.getTitle(), favoritemovieModel.getPoster_path(), favoritemovieModel.getRelease_date(), favoritemovieModel.getVote_average(), favoritemovieModel.getOverview(), favoritemovieModel.getVote_count(), favoritemovieModel.getGenre_ids());
                            favoriteViewModel.delete(favoriteMovies);
                        }

                    } else {

                    }

                }
            }

        });
//        Rest();
        return view;
    }

    public void update_Info(MovieModel movieModel, int page) {

        Log.e(TAG, "update_Info9988 first");
        if (movieModel != null) {
            Log.e(TAG, "update_Info9988 movieModel != null");

            checkInDatabase = true;
            favoriteToggle.setClickable(true);
            favoriteToggle.setChecked(true);
            favoritemovieModel = movieModel;
            favoriteViewModel.searchMovies(movieModel.getId()).observe(this, new Observer<MovieModel>() {
                @Override
                public void onChanged(MovieModel localMovieModel) {
                    if (localMovieModel != null) {
                        checkInDatabase = true;
                        favoriteToggle.setClickable(true);
                        favoriteToggle.setChecked(true);

                    } else {
                        checkInDatabase = false;
                        favoriteToggle.setChecked(false);
                        favoriteToggle.setClickable(true);
                    }
                }
            });

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path()).into(movieImage);
            movieTitle.setText(String.valueOf(movieModel.getTitle()));
            movieDescription.setText(String.valueOf(movieModel.getOverview()));
            movieVoteCount.setText(String.valueOf(movieModel.getVote_count()));
            movieRate.setRating(movieModel.getVote_average() / 2);
            GenresTypes.clear();

             Log.e(TAG, "genres.getGenres().size 22445573111339 : " + movieModel.getGenre_ids().size() + "-----" + movieModel.getGenre_ids().size());
            if (genres.getGenres() != null && movieModel.getGenre_ids() != null) {
                if (genres.getGenres().size() != 0 && movieModel.getGenre_ids().size() != 0) {
                    for (int i = 0; i < movieModel.getGenre_ids().size(); i++) {
                        for (int j = 0; j < genres.getGenres().size(); j++) {
                            Log.e(TAG, "genres.getGenres() 22445573111339 : " + j + "-----" + i);
                            Log.e(TAG, "genres.getGenres() 22445573111339 : " + genres.getGenres().get(j).getId() + "-----" + movieModel.getGenre_ids().get(i));
                            if (genres.getGenres().get(j).getId() == Integer.parseInt(movieModel.getGenre_ids().get(i))) {
                                Log.e(TAG, "genres.getGenres() 22445573111339 : " + genres.getGenres().get(j).getName());
                                GenresTypes.add(String.valueOf(genres.getGenres().get(j).getName()));
                            }
                        }
                    }
                    String listString = "";
                    for (String s : GenresTypes) {
                        listString += s + "   ";
                    }

                     movieGernres.setText(String.valueOf(listString));
                }
            }


        } else {
            Log.e(TAG, "update_Info9988 movieModel else ==== null");
            checkInDatabase = false;
            favoriteToggle.setChecked(false);
            favoriteToggle.setClickable(false);
        }


    }


}