package com.example.toptracertest.gif

import com.example.toptracertest.appdata.Gif
import com.example.toptracertest.network.GiphyApi
import com.example.toptracertest.network.data.NetworkResponse
import com.example.toptracertest.network.data.asSuccessfulNetworkResponse
import com.example.toptracertest.network.data.toGif

class GifRepository(private val api: GiphyApi) {
    suspend fun fetchTrendyGif(): NetworkResponse<Gif> {
        // The api key here is NOT EVER a good practice, but for time constraints it is placed here
        val response =
            kotlin.runCatching { api.getRandomGif(api_key = "1lc4CDQnea05CC4oFSfZOi0Fcs3EzTeC") }
        response.fold(
            onSuccess = {
                return it.toGif().asSuccessfulNetworkResponse()
            },
            onFailure = {
                return NetworkResponse.GeneralError("Something went wrong with your network call")
            }
        )
    }
}