package com.base.pokedex.data.model.viewstate

import com.base.pokedex.data.model.local.entity.PokemonInfoEntity

data class PokemonInfoState(
    val pokemonInfo: PokemonInfoEntity? = null,
    val error: String = "",
    val isLoading: Boolean = false
)