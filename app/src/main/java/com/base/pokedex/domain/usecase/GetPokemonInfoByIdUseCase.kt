package com.base.pokedex.domain.usecase

import com.base.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonInfoByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) =
        pokemonRepository.getPokemonInfoById(id)
}