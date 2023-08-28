package com.base.pokedex.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoveX(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)