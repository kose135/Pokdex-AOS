package com.base.pokedex.ui.screen.list

import com.base.pokedex.domain.entity.PokemonEntity

sealed class PokemonListState {
    data class Success(val pokemons: List<PokemonEntity>) : PokemonListState()
    data class Failure(val message: String) : PokemonListState()
    object Loading : PokemonListState()
}
