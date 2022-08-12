package com.denybrabo.fortesfilms.models

import androidx.lifecycle.MutableLiveData
import com.denybrabo.fortesfilms.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesSagaRepository {

    var listMovies = MutableLiveData<MutableList<MoviesSagaModel>>()
    var auxListMovies: MutableList<MoviesSagaModel>? = arrayListOf()

    fun getMovies(): MutableLiveData<MutableList<MoviesSagaModel>>?{

        var retrofit = Retrofit.Builder()
            .baseUrl("https://private-b34167-rvmarvel.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(ApiService::class.java)
        var call: Call<MutableList<MoviesSagaModel>> = service.getSaga()

        call.enqueue(object : Callback<MutableList<MoviesSagaModel>>{
            override fun onResponse(
                call: Call<MutableList<MoviesSagaModel>>,
                response: Response<MutableList<MoviesSagaModel>>
            ) {
                listMovies.value = response.body()
            }

            override fun onFailure(call: Call<MutableList<MoviesSagaModel>>, t: Throwable) {
                listMovies.value = auxListMovies
            }
        })
        return listMovies
    }
}