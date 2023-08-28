package com.base.pokedex.domain.usecase

import com.base.pokedex.data.repository.PokemonRepositoryImpl
import javax.inject.Inject

class GetPokemonInfoByNameUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepositoryImpl
) {
    suspend operator fun invoke(name: String) = pokemonRepository.getPokemonInfoByName(
        name
    )
}