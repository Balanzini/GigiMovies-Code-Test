package com.jose.gigimovies.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMovieDao {
  @Query("SELECT * FROM favouritemovieentity")
  fun getFavourites(): List<FavouriteMovieEntity>

  @Query("SELECT * FROM favouritemovieentity")
  fun getFlowFavourites(): Flow<List<FavouriteMovieEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(vararg movieEntity: FavouriteMovieEntity)

  @Delete
  fun delete(movieEntity: FavouriteMovieEntity)
}