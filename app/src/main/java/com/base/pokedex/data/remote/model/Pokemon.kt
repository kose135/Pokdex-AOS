package com.base.pokedex.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Pokemon(
    val name: String,
    val url: String,
) {
    val imgUrl: String
        get() {
            val index = url.split("/".toRegex()).dropLast(1).last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
        }
}