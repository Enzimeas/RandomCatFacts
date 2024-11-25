package com.example.randomcatfacts.ui.fragments

import retrofit2.Call
import retrofit2.http.GET

interface CatFactsApi {

    // Endpoint para pegar um fato aleatório de gato
    @GET("facts/random")
    fun getRandomCatFact(): Call<CatFact>
}
