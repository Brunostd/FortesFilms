package com.denybrabo.fortesfilms.api

import com.denybrabo.fortesfilms.models.MoviesSagaModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/saga")
    fun getSaga(): Call<MutableList<MoviesSagaModel>>

}