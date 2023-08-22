package com.base.pokedex.data.usecase

import com.base.pokedex.data.mapper.asEntity
import com.base.pokedex.data.model.remote.Result
import com.base.pokedex.data.model.remote.dto.PokemonInfo
import com.base.pokedex.data.model.remote.map
import com.base.pokedex.data.repository.PokemonRepositoryImpl
import com.base.pokedex.data.repository.interfaces.PokemonRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonInfoByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) = pokemonRepository.getPokemonInfoById(
        id
    ).map { result: Result<PokemonInfo> ->
        result.map { response ->
            response.asEntity()
        }
    }
}