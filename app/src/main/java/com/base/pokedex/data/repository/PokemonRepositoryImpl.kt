package com.base.pokedex.data.repository

import com.base.pokedex.data.model.local.dao.PokemonInfoDao
import com.base.pokedex.data.model.local.entity.PokemonInfoEntity
import com.base.pokedex.data.model.remote.PokeApiService
import com.base.pokedex.data.model.remote.dto.PokemonInfo
import com.base.pokedex.data.model.remote.Result
import com.base.pokedex.data.model.remote.response.PokemonListResponse
import com.base.pokedex.data.repository.interfaces.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val service: PokeApiService,
    private val pokemonInfoDao: PokemonInfoDao
) : BaseRepository(), PokemonRepository {

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<PokemonListResponse>> {
        return safeApiCall { service.getPokemonList(limit, offset) }
    }

    override suspend fun getPokemonInfoByName(pokemonName: String): Flow<Result<PokemonInfo>> {
        return safeApiCall { service.getPokemonInfoByName(pokemonName) }
    }

    override suspend fun getPokemonInfoById(pokemonId: Int): Flow<Result<PokemonInfo>> {
        return safeApiCall { service.getPokemonInfoById(pokemonId) }
    }

    override suspend fun upsertPokemonInfo(pokemonInfoEntity: PokemonInfoEntity) {
        pokemonInfoDao.upsertPokemonInfo(pokemonInfoEntity)
    }

}