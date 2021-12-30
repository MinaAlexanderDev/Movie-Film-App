package com.example.MyMovieFilmApp.ui.fragment.searchmovie;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyMovieFilmApp.R;
import com.example.MyMovieFilmApp.pojo.moviedata.MovieModel;
import com.example.MyMovieFilmApp.ui.fragment.fragmenttoprated.TopRateFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchMoviesAdapter extends RecyclerView.Adapter<SearchMoviesAdapter.PostViewHolder> {
    private static String TAG = "PostsAdapter";
    private List<MovieModel> postList = new ArrayList<>();
    private SearchFragment mOnClick;

    public SearchMoviesAdapter(SearchFragment mOnClick) {
        this.mOnClick = mOnClick;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view, mOnClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
//        holder.moveName.setText(String.valueOf(postList.get(position).getId()));
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + postList.get(position).getPoster_path()).into(holder.movieImage);
        holder.movie_ratingBar.setRating(postList.get(position).getVote_average() / 2);
        holder.movieTitle.setText(String.valueOf(postList.get(position).getTitle()));
        holder.moveVote.setText(String.valueOf(postList.get(position).getVote_average()));
         Log.e(TAG, "getGenre_ids : " + postList.get(position).getGenre_ids());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setList(List<MovieModel> postList) {
        this.postList = postList;
        Log.e(TAG, "postListgetItemCount : " + postList.size());
        notifyDataSetChanged();
    }

    public interface OnItemListener {
        void onItemClick(int postion);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieImage;
        TextView movieTitle, moveVote;
        SearchFragment onItemListener;
        RatingBar movie_ratingBar;

        public PostViewHolder(@NonNull View itemView, SearchFragment onItemListener) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.Movie_title);
            moveVote = itemView.findViewById(R.id.move_vote);
            movieImage = itemView.findViewById(R.id.movieImage);
            movie_ratingBar = itemView.findViewById(R.id.movie_ratingBar);
             this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onItemClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}