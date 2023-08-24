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
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "weight")
    val weight: Int,
    @Json(name = "base_experience")
    val baseExperience: Int,
    @Json(name = "stats")
    val stats: List<Stat>,
    @Json(name = "types")
    val types: List<Type>,
) {
    val imgUrl: String
        get() {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        }
}