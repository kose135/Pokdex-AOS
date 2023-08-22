package com.base.pokedex.data.model.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationI(
    @Json(name = "red-blue")
    val redBlue: RedBlue,
    @Json(name = "yellow")
    val yellow: Yellow
)