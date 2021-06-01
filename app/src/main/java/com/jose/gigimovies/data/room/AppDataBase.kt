package com.jose.gigimovies.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteMovieEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
  abstract fun movieDao() : FavouriteMovieDao

  companion object{
    private var instance: AppDataBase? = null
    private const val DATABASE_NAME = "Gigimovies.db"

    fun getInstance(context: Context): AppDataBase{
      return instance ?: synchronized(this){
        instance ?: buildDatabase(context).also {instance = it}
      }
    }

    private fun buildDatabase(context: Context): AppDataBase {
      return Room.databaseBuilder(
        context, AppDataBase::class.java,
        DATABASE_NAME
      ).build()
    }
  }
}