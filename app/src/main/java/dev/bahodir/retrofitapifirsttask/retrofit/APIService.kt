package dev.bahodir.retrofitapifirsttask.retrofit

import dev.bahodir.retrofitapifirsttask.user.UserOneItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("api/v2/entries/en/{word}")
    fun getAPIService(@Path("word") word: String) : Call<UserOneItem>
}