package com.base.pokedex.domain.repository

import com.base.pokedex.domain.entity.PokemonInfoEntity
import com.base.pokedex.domain.Result
import com.base.pokedex.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Flow<Result<List<PokemonEntity>>>

    suspend fun getPokemonInfoByName(pokemonName: String): Flow<Result<PokemonInfoEntity>>

    suspend fun getPokemonInfoById(pokemonId: Int): Flow<Result<PokemonInfoEntity>>

    suspend fun upsertPokemonInfo(pokemonInfoEntity: PokemonInfoEntity)

}