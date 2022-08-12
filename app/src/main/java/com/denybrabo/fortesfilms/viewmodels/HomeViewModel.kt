package com.denybrabo.fortesfilms.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denybrabo.fortesfilms.models.MoviesSagaModel
import com.denybrabo.fortesfilms.models.MoviesSagaRepository

class HomeViewModel: ViewModel() {

    var _movies: MutableLiveData<MutableList<MoviesSagaModel>>? = null
    var filmes: LiveData<MutableList<MoviesSagaModel>>? = _movies

    fun getMovies(): LiveData<MutableList<MoviesSagaModel>>?{
        _movies = MoviesSagaRepository().getMovies()
        return _movies
    }
}