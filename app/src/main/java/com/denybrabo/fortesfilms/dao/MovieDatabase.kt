package com.denybrabo.fortesfilms.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denybrabo.fortesfilms.models.MoviesFavoriteModel
import com.denybrabo.fortesfilms.models.MoviesSagaModel

@Database(entities = [MoviesSagaModel::class, MoviesFavoriteModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDataBase(context: Context): MovieDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}