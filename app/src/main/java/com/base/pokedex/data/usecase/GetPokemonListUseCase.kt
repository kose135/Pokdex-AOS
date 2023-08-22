package com.base.pokedex.data.usecase

import com.base.pokedex.data.mapper.asEntity
import com.base.pokedex.data.model.remote.Result
import com.base.pokedex.data.model.remote.map
import com.base.pokedex.data.model.remote.response.PokemonListResponse
import com.base.pokedex.data.repository.PokemonRepositoryImpl
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepositoryImpl
) {
    suspend operator fun invoke(limit: Int, offset: Int) = pokemonRepository.getPokemonList(
        limit = limit,
        offset = offset
    ).map { result: Result<PokemonListResponse> ->
        result.map { response ->
            response.results.asEntity()
        }
    }
}