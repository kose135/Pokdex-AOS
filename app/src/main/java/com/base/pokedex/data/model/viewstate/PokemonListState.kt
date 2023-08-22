package com.base.pokedex.data.model.viewstate

import com.base.pokedex.data.model.local.entity.PokemonEntity

data class PokemonListState(
    val pokemons: List<PokemonEntity> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)