package com.denybrabo.fortesfilms.dao

import androidx.lifecycle.LiveData
import com.denybrabo.fortesfilms.models.MoviesFavoriteModel
import com.denybrabo.fortesfilms.models.MoviesSagaModel

class MovieRepository(private val movieDao: MoviesDao) {

    val readAllData: LiveData<MutableList<MoviesSagaModel>> = movieDao.readAllMovies()
    val readAllFavoriteData: LiveData<MutableList<MoviesFavoriteModel>> = movieDao.favoriteMovies()

    suspend fun addMovie(moviesSagaModel: MoviesSagaModel){
        movieDao.addMovie(moviesSagaModel)
    }

    suspend fun addFavoriteMovie(moviesFavoriteModel: MoviesFavoriteModel){
        movieDao.addFavoriteMovie(moviesFavoriteModel)
    }

    suspend fun updateMovie(moviesSagaModel: MoviesSagaModel){
        movieDao.updateMovie(moviesSagaModel)
    }

    suspend fun deleteMovie(moviesSagaModel: MoviesSagaModel){
        movieDao.deleteMovie(moviesSagaModel)
    }

    suspend fun deleteFavoriteMovie(moviesFavoriteModel: MoviesFavoriteModel){
        movieDao.deleteFavoriteMovie(moviesFavoriteModel)
    }

    suspend fun deleteAllMovies(){
        movieDao.deleteAllMovies()
    }

    suspend fun deleteAllFavoriteMovies(){
        movieDao.deleteAllFavoriteMovies()
    }
}