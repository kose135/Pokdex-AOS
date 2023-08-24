package com.base.pokedex.data.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.base.pokedex.data.model.remote.dto.Ability
import com.base.pokedex.data.model.remote.dto.Form
import com.base.pokedex.data.model.remote.dto.GameIndice
import com.base.pokedex.data.model.remote.dto.Move
import com.base.pokedex.data.model.remote.dto.PokemonInfo
import com.base.pokedex.data.model.remote.dto.Species
import com.base.pokedex.data.model.remote.dto.Sprites
import com.base.pokedex.data.model.remote.dto.Stat
import com.base.pokedex.data.model.remote.dto.Type

@Entity
data class PokemonInfoEntity(
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<String>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    var update: String = "",
    @PrimaryKey
    val id: Int,
)