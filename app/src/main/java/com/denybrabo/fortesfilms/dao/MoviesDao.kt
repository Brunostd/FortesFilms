package com.denybrabo.fortesfilms.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.denybrabo.fortesfilms.models.MoviesSagaModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(moviesSagaModel: MoviesSagaModel)

    @Update()
    fun updateMovie(moviesSagaModel: MoviesSagaModel)

    @Delete()
    fun deleteMovie(moviesSagaModel: MoviesSagaModel)

    @Query("DELETE FROM user_table")
    fun deleteAllMovies()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllMovies(): LiveData<MutableList<MoviesSagaModel>>
}