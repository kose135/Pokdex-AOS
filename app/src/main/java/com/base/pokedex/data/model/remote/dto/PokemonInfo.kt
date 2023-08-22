package com.base.pokedex.data.model.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfo(
//    val abilities: List<Ability>,
//    val forms: List<Form>,
//    @Json(name = "game_indices")
//    val gameIndices: List<GameIndice>,
//    val heldItems: List<Any>,
//    @Json(name = "is_default")
//    val isDefault: Boolean,
//    @Json(name = "location_area_encounters")
//    val locationAreaEncounters: String,
//    val moves: List<Move>,
//    @Json(name = "past_types")
//    val pastTypes: List<Any>,
//    val species: Species,
//    val sprites: Sprites,
    val id: Int,
    val name: String,
    @Json(name = "base_experience")
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val stats: List<Stat>,
    val types: List<Type>,
)