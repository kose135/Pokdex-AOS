package com.base.pokedex.presentation.screen.detail

import com.base.pokedex.domain.entity.PokemonInfoEntity

sealed class PokemonInfoState {
    data class Success(val pokemonInfoEntity: PokemonInfoEntity) : PokemonInfoState()
    data class Failure(val message: String) : PokemonInfoState()
    object Loading : PokemonInfoState()
}