package com.base.pokedex.data.remote.datasource

import com.base.pokedex.data.remote.PokeApiService
import com.base.pokedex.data.remote.datasource.interfaces.PokemonRemoteDataSource
import com.base.pokedex.data.remote.model.PokemonInfo
import com.base.pokedex.data.remote.response.PokemonListResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val service: PokeApiService,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListResponse> =
        service.getPokemonList(limit, offset)

    override suspend fun getPokemonInfoByName(pokemonName: String): Response<PokemonInfo> =
        service.getPokemonInfoByName(pokemonName)

    override suspend fun getPokemonInfoById(pokemonId: Int): Response<PokemonInfo> =
        service.getPokemonInfoById(pokemonId)
}