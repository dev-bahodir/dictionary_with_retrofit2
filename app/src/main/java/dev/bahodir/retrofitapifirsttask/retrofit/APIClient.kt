package dev.bahodir.retrofitapifirsttask.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_USL = "https://api.dictionaryapi.dev/"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_USL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}