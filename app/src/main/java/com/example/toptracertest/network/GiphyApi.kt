package com.example.toptracertest.network

import com.example.toptracertest.network.data.RandomGifResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("/v1/gifs/trending")
    suspend fun getRandomGif(
        @Query("api_key") api_key: String,
        @Query("tag") tag: String = "",
        @Query("rating") rating: String = "g"
    ): RandomGifResponse


    companion object {

        val basePath: String = "https://api.giphy.com"

        val instance: GiphyApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(basePath)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            retrofit.create(GiphyApi::class.java)
        }
    }

}