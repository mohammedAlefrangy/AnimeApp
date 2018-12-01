package com.example.hmod_.animeapp.Adapter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hmod_.animeapp.DataEntity.Anime;
import com.example.hmod_.animeapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdapterForAnime extends RecyclerView.Adapter<AdapterForAnime.MyViewHolder> implements Filterable {


    private ArrayList<Anime> animes;
    private ArrayList<Anime> animeSearch;
    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private static final String TAG = "AdapterForAnime";




    public interface OnItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    public AdapterForAnime(ArrayList<Anime> animes, Context context, OnItemClickListener onItemClickListener) {
        this.animes = animes;
        this.context = context;
        this.onItemClickListener = onItemClickListener;

        animeSearch = new ArrayList<>(animes);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_image_main_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Anime item = animes.get(position);
        String title = item.getCanonicalTitle();
        holder.titleForAnime.setText(title);
        Log.d(TAG, "onBindViewHolder: " + title);
        Object image = item.getPosterImage();
//
        Picasso.with(context).load(String.valueOf(image)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final SimpleDraweeView imageView;
        final TextView titleForAnime;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_movie_poster);
            titleForAnime = itemView.findViewById(R.id.tv_movie_title);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onItemClickListener.onListItemClick(clickedPosition);
        }


    }


    public void clear() {
        animes.clear();
        notifyDataSetChanged();
    }

    public void addِAnime(Anime anime) {
        animes.add(anime);
        notifyItemInserted(animes.size() - 1);
    }


    //
//    public void addAll(List<Movie> movieList) {
//        if (movieList != null) {
//            for (Movie movie : movieList) {
//                addMovie(movie);
//            }
//        }
//    }
//
//    public void addAllFavorites(List<FavoritesMovieEntity> favoritesMovieEntities) {
//        for (FavoritesMovieEntity favoritesMovieEntity : favoritesMovieEntities) {
//            addMovie(new Movie(favoritesMovieEntity.getOverViewForMovie(), favoritesMovieEntity.getPosterForMovie(),
//                    favoritesMovieEntity.getReleaseDateForMovie(), favoritesMovieEntity.getNameForMovie(),
//                    favoritesMovieEntity.getVoteAverageForMovie(), String.valueOf(favoritesMovieEntity.getIdForMovie())
//            ));
//        }
//    }
    public void setTasks(ArrayList<Anime> taskEntries) {
        animes = taskEntries;
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Anime> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length()==0){
                filteredList.addAll(animeSearch);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Anime item : animeSearch){
                    if (item.getCanonicalTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList ;

            return results ;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            animes.clear();
            animes.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };


}
