package com.example.MyMovieFilmApp.ui.fragment.fragmenttoprated;

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

public class TopRateFragment extends Fragment implements TopRatedMovieAdapter.OnItemListener {

    private TopRatedViewModel mViewModel;
    private static String TAG = "WatchNowFragment";
    int currentItem, totalItems, scrollOutItems;
    private TopRatedViewModel topRatedViewModel;
    private List<MovieModel> Postes = new ArrayList<>();
    private TopRatedMovieAdapter adapter;
    private onHeatLisenerTopRate onHeatLisenerTopRate;
    private boolean isLoading = true;
    private int page_number = 1;
    private boolean isScrolling = false;
    private LinearLayoutManager layoutManager;
    private int pagesNumber = 0;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TopRatedMovieAdapter.OnItemListener onItemListener;


    public static TopRateFragment newInstance() {
        return new TopRateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
        recyclerView = rootView.findViewById(R.id.recycler);
        progressBar = rootView.findViewById(R.id.progressBar);
        return rootView;
//        return inflater.inflate(R.layout.fragment_top_rated, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         topRatedViewModel = new ViewModelProvider(this).get(TopRatedViewModel.class);
        adapter = new TopRatedMovieAdapter(this);
        layoutManager = new LinearLayoutManager(getActivity());

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
//                        binding.progressBar
                        progressBar.setVisibility(View.VISIBLE);
                        page_number = page_number + 1;
                        topRatedViewModel.fillData(DataAuth.API_KEY, page_number);
                    } else {
//                        Toast.makeText(getContext(), "there is no more movies!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        checkInternet();
    }

    public void LoadData() {
        topRatedViewModel.fillData(DataAuth.API_KEY, page_number);
         topRatedViewModel.postsMutableLiveData.observe(getActivity(), new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                 progressBar.setVisibility(View.VISIBLE);

                Log.e(TAG, "datap 1: " + data);

                Postes.addAll(data.getMovies());
                pagesNumber = data.getTotal_pages();

                Log.e(TAG, "pagesNumber : " + pagesNumber);

                if (Postes.size() == 0) {
                    Log.e(TAG, "datap 2 : " + data);

                } else {
                    Log.e(TAG, "datap 3 : " + Postes.size());
                    adapter.setList(Postes);
                    adapter.notifyDataSetChanged();
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

    private void checkInternet() {
        Log.e(TAG, "VersionCheck" + "CheckInternet");
        ConnectivityManager cManager;
        AlertDialog.Builder mBuilder_net;
        AlertDialog alertDialog_net;

        Log.e(TAG, "isNetworkConnected : " + isNetworkConnected() + "  --- isInternetAvailable : " + isInternetAvailable());
        if (isNetworkConnected()) {
            LoadData();

        } else {
            mBuilder_net = new AlertDialog.Builder(getContext(), AlertDialog.THEME_HOLO_LIGHT);
            mBuilder_net.setTitle("Net Work Bad!");
            mBuilder_net.setMessage("please try again later");
            mBuilder_net.setIcon(R.mipmap.ic_launcher);
            mBuilder_net.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialoge, int i) {
                    checkInternet();
//                    Retry.setVisibility(View.VISIBLE);
//                    Exit.setVisibility(View.VISIBLE);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });;
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
        onHeatLisenerTopRate.heating(Postes.get(postion),2);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onHeatLisenerTopRate = (onHeatLisenerTopRate) context;
    }

    public interface onHeatLisenerTopRate {
        public void heating(MovieModel position,int page);
    }
}