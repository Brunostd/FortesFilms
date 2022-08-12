package com.denybrabo.fortesfilms.dao

import androidx.lifecycle.LiveData
import com.denybrabo.fortesfilms.models.MoviesSagaModel

class MovieRepository(private val movieDao: MoviesDao) {

    val readAllData: LiveData<MutableList<MoviesSagaModel>> = movieDao.readAllMovies()

    suspend fun addMovie(moviesSagaModel: MoviesSagaModel){
        movieDao.addMovie(moviesSagaModel)
    }

    suspend fun updateMovie(moviesSagaModel: MoviesSagaModel){
        movieDao.updateMovie(moviesSagaModel)
    }

    suspend fun deleteMovie(moviesSagaModel: MoviesSagaModel){
        movieDao.deleteMovie(moviesSagaModel)
    }

    suspend fun deleteAllMovies(){
        movieDao.deleteAllMovies()
    }
}