package com.base.pokedex.domain.usecase

import com.base.pokedex.data.repository.PokemonRepositoryImpl
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepositoryImpl
) {
    suspend operator fun invoke(limit: Int, offset: Int) = pokemonRepository.getPokemonList(
        limit = limit,
        offset = offset
    )
}