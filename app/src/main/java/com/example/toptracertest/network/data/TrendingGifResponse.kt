package com.example.toptracertest.network.data

import com.example.toptracertest.appdata.Gif
import com.squareup.moshi.Json

data class RandomGifResponse(
    @field:Json(name = "data")
    val data: List<Data>,
    @field:Json(name = "meta")
    val meta: Meta
)

data class Data(
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "username")
    val username: String?,
    @field:Json(name = "title")
    val title: String?,
)

data class Meta(
    @field:Json(name = "msg")
    val url: String?,
    @field:Json(name = "status")
    val title: Int?,
    @field:Json(name = "response_id")
    val username: String?
)

fun RandomGifResponse.toGif() = Gif(
    url = data.first().url ?: "",
    author = data.first().username ?: "",
    title = data.first().title ?: ""
)

//fun RandomGifResponse.toGif() = Gif(url = meta.url ?: "", author = meta.username ?: "", title = meta.title.toString() ?: "")