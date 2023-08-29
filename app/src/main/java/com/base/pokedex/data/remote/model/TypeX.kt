package com.base.pokedex.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeX(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)