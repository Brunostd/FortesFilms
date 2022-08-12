package com.denybrabo.fortesfilms.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denybrabo.fortesfilms.dao.MovieDatabase
import com.denybrabo.fortesfilms.dao.MovieRepository
import com.denybrabo.fortesfilms.models.MoviesFavoriteModel
import com.denybrabo.fortesfilms.models.MoviesSagaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<MutableList<MoviesSagaModel>>
    val readAllFavoriteData: LiveData<MutableList<MoviesFavoriteModel>>
    val repository: MovieRepository

    init {
        var movieDao = MovieDatabase.getDataBase(application).movieDao()
        repository = MovieRepository(movieDao)
        readAllData = repository.readAllData
        readAllFavoriteData = repository.readAllFavoriteData
    }

    fun addMovie(moviesSagaModel: MoviesSagaModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovie(moviesSagaModel)
        }
    }

    fun addFavoriteMovie(moviesFavoriteModel: MoviesFavoriteModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavoriteMovie(moviesFavoriteModel)
        }
    }

    fun updateMovie(moviesSagaModel: MoviesSagaModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateMovie(moviesSagaModel)
        }
    }

    fun deleteMovie(moviesSagaModel: MoviesSagaModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMovie(moviesSagaModel)
        }
    }

    fun deleteFavoriteMovie(moviesFavoriteModel: MoviesFavoriteModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFavoriteMovie(moviesFavoriteModel)
        }
    }

    fun deleteAllMovies(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllMovies()
        }
    }

    fun deleteAllFavoriteMovies(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllFavoriteMovies()
        }
    }

}