package com.base.pokedex.data.repository.interfaces

import com.base.pokedex.data.model.local.entity.PokemonInfoEntity
import com.base.pokedex.data.model.remote.Result
import com.base.pokedex.data.model.remote.dto.PokemonInfo
import com.base.pokedex.data.model.remote.response.PokemonListResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Flow<Result<PokemonListResponse>>

    suspend fun getPokemonInfoByName(pokemonName: String): Flow<Result<PokemonInfo>>

    suspend fun getPokemonInfoById(pokemonId: Int): Flow<Result<PokemonInfo>>

    suspend fun upsertPokemonInfo(pokemonInfoEntity: PokemonInfoEntity)

}