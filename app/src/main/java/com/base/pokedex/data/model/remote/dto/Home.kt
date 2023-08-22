package com.base.pokedex.data.model.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Home(
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_female")
    val frontFemale: Any,
    @Json(name = "front_shiny")
    val frontShiny: String,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: Any
)