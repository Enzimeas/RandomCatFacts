package com.example.randomcatfacts.data.api

import com.example.randomcatfacts.network.CatImage
import retrofit2.Call
import retrofit2.http.GET

interface CatApi {

    // Endpoint para pegar uma imagem aleatória de gato
    @GET("images/search")
    fun getRandomCatImage(): Call<List<CatImage>>
}
