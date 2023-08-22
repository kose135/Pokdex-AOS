package com.base.pokedex.data.usecase

import com.base.pokedex.data.mapper.asEntity
import com.base.pokedex.data.model.remote.Result
import com.base.pokedex.data.model.remote.dto.PokemonInfo
import com.base.pokedex.data.model.remote.map
import com.base.pokedex.data.repository.PokemonRepositoryImpl
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonInfoByNameUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepositoryImpl
) {
    suspend operator fun invoke(name: String) = pokemonRepository.getPokemonInfoByName(
        name
    ).map { result: Result<PokemonInfo> ->
        result.map { response ->
            response.asEntity()
        }
    }
}