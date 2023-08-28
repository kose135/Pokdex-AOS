package com.base.pokedex.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonInfoEntity(
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<String>,
    val stats: List<Pair<String, Int>>,
    var update: String = "",
    @PrimaryKey
    val id: Int,
)