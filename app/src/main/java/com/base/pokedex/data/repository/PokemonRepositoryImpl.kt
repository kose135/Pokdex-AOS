package com.base.pokedex.data.repository

import com.base.pokedex.data.local.dao.PokemonInfoDao
import com.base.pokedex.data.mapper.asEntity
import com.base.pokedex.domain.entity.PokemonInfoEntity
import com.base.pokedex.domain.Result
import com.base.pokedex.data.remote.PokeApiService
import com.base.pokedex.data.remote.dto.PokemonInfo
import com.base.pokedex.data.remote.response.PokemonListResponse
import com.base.pokedex.domain.entity.PokemonEntity
import com.base.pokedex.domain.mapper
import com.base.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val service: PokeApiService,
    private val pokemonInfoDao: PokemonInfoDao
) : BaseRepository(), PokemonRepository {


    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<List<PokemonEntity>>> {
        return safeApiCall { service.getPokemonList(limit, offset) }
            .mapper { response: PokemonListResponse ->
                response.results.asEntity()
            }
    }

    override suspend fun getPokemonInfoByName(pokemonName: String): Flow<Result<PokemonInfoEntity>> {
        return safeApiCall { service.getPokemonInfoByName(pokemonName) }
            .mapper { response: PokemonInfo ->
                response.asEntity()
            }
    }

    override suspend fun getPokemonInfoById(pokemonId: Int): Flow<Result<PokemonInfoEntity>> {
        return safeApiCall { service.getPokemonInfoById(pokemonId) }
            .mapper { response: PokemonInfo ->
                response.asEntity()
            }
    }

    override suspend fun upsertPokemonInfo(pokemonInfoEntity: PokemonInfoEntity) {
        pokemonInfoDao.upsertPokemonInfo(pokemonInfoEntity)
    }

}