package com.example.MyMovieFilmApp.ui.fragment.favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyMovieFilmApp.R;
import com.example.MyMovieFilmApp.data.locadata.favorite.FavoriteMovies;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteMovieAdapter.OnItemListener {

    private static String TAG = "WatchNowFragment";
    int currentItem, totalItems, scrollOutItems;
    private FavoriteViewModel favoriteViewModel;
    private List<MovieModel> Postes = new ArrayList<>();
    private FavoriteMovieAdapter adapter;
    private onHeatLisener onHeatLisener;
    private boolean isLoading = true;
    private int page_number = 1;
    private boolean isScrolling = false;
    private LinearLayoutManager layoutManager;
    private int pagesNumber = 0;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FavoriteMovieAdapter.OnItemListener onItemListener;
    View rootView;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = rootView.findViewById(R.id.recycler);
        progressBar = rootView.findViewById(R.id.progressBar);
        return rootView;

    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new FavoriteMovieAdapter(this);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.GONE);

        ///////////////////
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAllMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> favoriteMovies) {
                Postes=favoriteMovies;
                adapter.setList(favoriteMovies);
            }
        });

        /////////////////////////////////

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem + scrollOutItems == totalItems)) {
                    Log.e(TAG, "!!!isLoading : " + "   data.getTotal_pages() : " + pagesNumber);

                    if (page_number <= pagesNumber) {
                         progressBar.setVisibility(View.VISIBLE);
                        page_number = page_number + 1;
                     } else {
                     }

                }
            }
        });

    }


    @Override
    public void onItemClick(int postion) {
        Log.e(TAG, "postion :" + postion);
        onHeatLisener.heating(Postes.get(postion),4);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onHeatLisener = (onHeatLisener) context;
    }

    public interface onHeatLisener {
        public void heating(MovieModel position,int page);
    }

}