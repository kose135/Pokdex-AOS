package com.base.pokedex.data.model.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VersionGroup(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)