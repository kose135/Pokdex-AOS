package com.base.pokedex.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Other(
    @Json(name = "dream_world")
    val dreamWorld: DreamWorld,
    @Json(name = "home")
    val home: Home,
    @Json(name = "official-artwork")
    val officialArtwork: OfficialArtwork
)