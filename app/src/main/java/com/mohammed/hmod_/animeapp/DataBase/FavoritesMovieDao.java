package com.mohammed.hmod_.animeapp.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface FavoritesMovieDao {
    @Query("SELECT * FROM favoritesAnime")
    LiveData<List<FavoritesaAnimeEntity>> loadAllMovies();

    @Insert
    void insertMovie(FavoritesaAnimeEntity movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(FavoritesaAnimeEntity movie);

    @Delete
    void deleteMovie(FavoritesaAnimeEntity movie);

    @Query("SELECT * FROM favoritesAnime WHERE id == :id")
    FavoritesaAnimeEntity loadMovieById(int id);

}
