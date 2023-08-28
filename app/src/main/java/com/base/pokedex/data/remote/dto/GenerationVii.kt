package com.base.pokedex.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationVii(
    @Json(name = "icons")
    val icons: Icons,
    @Json(name = "ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon
)