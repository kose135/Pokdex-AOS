package com.base.pokedex.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    val url: String,
    val imageUrl: String,
    @PrimaryKey
    val name: String,
)