package com.example.MyMovieFilmApp.ui.fragment.searchmovie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyMovieFilmApp.R;
import com.example.MyMovieFilmApp.pojo.moviedata.Data;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;
import com.example.MyMovieFilmApp.utilities.DataAuth;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchMoviesAdapter.OnItemListener {

    private static String TAG = "SearchFragment";
    int currentItem, totalItems, scrollOutItems;
    private SearchViewModel searchViewModel;
    private List<MovieModel> Postes;
    private SearchMoviesAdapter adapter;
    private onHeatLisenerSearch onHeatLisenerSearch;
    private boolean isLoading = true;
    private int page_number = 1;
    private boolean isScrolling = false;
    private LinearLayoutManager layoutManager;
    private int pagesNumber = 0;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private EditText moveName;
    private ImageButton searchMovie;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = rootView.findViewById(R.id.recycler);
        progressBar = rootView.findViewById(R.id.progressBar);
        moveName = rootView.findViewById(R.id.movie_name);
        searchMovie = rootView.findViewById(R.id.search_movie);
        searchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternet();
            }
        });
        return rootView;
     }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        adapter = new SearchMoviesAdapter(this);
        layoutManager = new LinearLayoutManager(getActivity());
        Postes = new ArrayList<>();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.GONE);

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
                        searchViewModel.fillData(DataAuth.API_KEY, String.valueOf(moveName.getText()), page_number);
                    } else {
                     }

                }
            }
        });
        checkInternet();
    }

    public void LoadData(String key, String moviename, int pagenumber) {

        Log.e(TAG, "String.valueOf(moveName.getText()): " + Postes.size());
        Log.e(TAG, "String.valueOf(moveName.getText()): " + String.valueOf(moveName.getText()));
        searchViewModel.fillData(key, String.valueOf(moviename), pagenumber);
        if (!String.valueOf(moviename).equals(null) && !String.valueOf(moviename).equals("null") && !String.valueOf(moviename).equals("")) {
            searchViewModel.postsMutableLiveData.observe(getActivity(), new Observer<Data>() {
                @Override
                public void onChanged(Data data) {
                    progressBar.setVisibility(View.VISIBLE);

                    Log.e(TAG, "datap5588 1: " + data);
                    Log.e(TAG, "datap5588 1: " + String.valueOf(moveName.getText()).equals(String.valueOf(moviename)));
                    Log.e(TAG, "datap5588 1: " + String.valueOf(moveName.getText()) + " -----" + String.valueOf(moviename));
                    if (!String.valueOf(moveName.getText()).equals(moviename)) {
                        Postes.clear();
                        adapter.notifyDataSetChanged();
                    }

                    Postes.addAll(data.getMovies());
                    Log.e(TAG, "String.valueOf(moveName.getText()) after: " + Postes.size());
                    pagesNumber = data.getTotal_pages();

                    Log.e(TAG, "pagesNumber : " + pagesNumber);

                    if (Postes.size() == 0) {
                        Log.e(TAG, "datap 2 : " + data);

                    } else {
                        Log.e(TAG, "datap 3 : " + Postes.size());
                        adapter.setList(Postes);
                        adapter.notifyDataSetChanged();
//                    binding.progressBar.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//                        }
//                    }, 5000);


                    }
//                for(int i=0;i<Postes.size();i++){
//                    Log.e(TAG,"uri : "+ Postes.get(i).getUri()+"\n"+
//                            "url : "+Postes.get(i).getUrl()+"\n"+
//                            "id : "+Postes.get(i).getId());
//                }

                }

            });
        }


    }

    private void checkInternet() {
        Log.e(TAG, "VersionCheck" + "CheckInternet");
        ConnectivityManager cManager;
        AlertDialog.Builder mBuilder_net;
        AlertDialog alertDialog_net;

        Log.e(TAG, "isNetworkConnected : " + isNetworkConnected() + "  --- isInternetAvailable : " + isInternetAvailable());
        if (isNetworkConnected()) {
            LoadData(DataAuth.API_KEY, String.valueOf(moveName.getText()), page_number);

        } else {
            mBuilder_net = new AlertDialog.Builder(getContext(), AlertDialog.THEME_HOLO_LIGHT);
            mBuilder_net.setTitle("Net Work Bad!");
            mBuilder_net.setMessage("please try again later");
            mBuilder_net.setIcon(R.mipmap.ic_launcher_round);
            mBuilder_net.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialoge, int i) {
                    checkInternet();

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            alertDialog_net = mBuilder_net.create();
            alertDialog_net.show();
            alertDialog_net.setCancelable(false);
            alertDialog_net.setCanceledOnTouchOutside(false);
        }
    }

    public boolean isNetworkConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onItemClick(int postion) {
        Log.e(TAG, "postion :" + postion);
        onHeatLisenerSearch.heating(Postes.get(postion),3);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onHeatLisenerSearch = (SearchFragment.onHeatLisenerSearch) context;
    }

    public interface onHeatLisenerSearch {
        public void heating(MovieModel position,int page);
    }
}