package com.denybrabo.fortesfilms.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.denybrabo.fortesfilms.models.MoviesFavoriteModel
import com.denybrabo.fortesfilms.models.MoviesSagaModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(moviesSagaModel: MoviesSagaModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteMovie(moviesFavoriteModel: MoviesFavoriteModel)

    @Update()
    fun updateMovie(moviesSagaModel: MoviesSagaModel)

    @Delete()
    fun deleteMovie(moviesSagaModel: MoviesSagaModel)

    @Delete()
    fun deleteFavoriteMovie(moviesFavoriteModel: MoviesFavoriteModel)

    @Query("DELETE FROM user_table")
    fun deleteAllMovies()

    @Query("DELETE FROM favorite_table")
    fun deleteAllFavoriteMovies()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllMovies(): LiveData<MutableList<MoviesSagaModel>>

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun favoriteMovies(): LiveData<MutableList<MoviesFavoriteModel>>
}